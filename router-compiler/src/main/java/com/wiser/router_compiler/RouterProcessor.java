package com.wiser.router_compiler;

import com.google.auto.service.AutoService;
import com.wiser.router_annotation.ClassCreateProxy;
import com.wiser.router_annotation.Router;

import java.io.IOException;
import java.io.Writer;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import javax.annotation.processing.AbstractProcessor;
import javax.annotation.processing.Filer;
import javax.annotation.processing.Messager;
import javax.annotation.processing.ProcessingEnvironment;
import javax.annotation.processing.Processor;
import javax.annotation.processing.RoundEnvironment;
import javax.lang.model.SourceVersion;
import javax.lang.model.element.Element;
import javax.lang.model.element.TypeElement;
import javax.tools.Diagnostic;
import javax.tools.JavaFileObject;

@AutoService(Processor.class)
public class RouterProcessor extends AbstractProcessor {

	private Filer							filer;

	private Messager						messager;

	private Map<String, ClassCreateProxy>	proxyActivityMap	= new HashMap<>();

	private Map<String, ClassCreateProxy>	proxyProviderMap	= new HashMap<>();

	@Override public synchronized void init(ProcessingEnvironment processingEnvironment) {
		super.init(processingEnvironment);
		messager = processingEnvironment.getMessager();
		filer = processingEnvironment.getFiler();
	}

	/**
	 * 声名注解处理器要处理的注解
	 *
	 * @return
	 */
	@Override public Set<String> getSupportedAnnotationTypes() {
		Set<String> types = new HashSet<>();
		types.add(Router.class.getCanonicalName());
		return types;
	}

	/**
	 * 声名注解处理器支持的版本
	 *
	 * @return
	 */
	@Override public SourceVersion getSupportedSourceVersion() {
		return processingEnv.getSourceVersion();
	}

	/**
	 * 处理想要处理的事件
	 *
	 * @param set
	 * @param roundEnvironment
	 * @return
	 */
	@Override public boolean process(Set<? extends TypeElement> set, RoundEnvironment roundEnvironment) {
		messager.printMessage(Diagnostic.Kind.NOTE, "processing...");
		// 所有注解
		Set<? extends Element> elements = roundEnvironment.getElementsAnnotatedWith(Router.class);
		for (Element element : elements) {
			TypeElement typeElement = (TypeElement) element;
			Router router = typeElement.getAnnotation(Router.class);
			// 获取key
			String keyPath = router.path();
			// 获取key
			String keyProvider = router.provider();
			// 获取带有包名的类名
			String activityName = typeElement.getQualifiedName().toString();
			// 获取存储的创建的类对象
			if (!"".equals(keyPath)){
				ClassCreateProxy proxyA = proxyActivityMap.get(keyPath);
				if (proxyA == null) {
					proxyA = new ClassCreateProxy(keyPath, activityName);
					proxyActivityMap.put(keyPath, proxyA);
				}
			}
			// 获取存储的创建的类对象
			if (!"".equals(keyProvider)){
				ClassCreateProxy proxyP = proxyProviderMap.get(keyProvider);
				if (proxyP == null) {
					proxyP = new ClassCreateProxy(keyProvider, activityName);
					proxyProviderMap.put(keyProvider, proxyP);
				}
			}
		}
		if (proxyActivityMap.size() > 0) {
			// 开始写Java文件
			for (String key : proxyActivityMap.keySet()) {
				ClassCreateProxy proxy = proxyActivityMap.get(key);
				try {
					JavaFileObject jfo = filer.createSourceFile(proxy.getClassActivityFullName());
					Writer writer = jfo.openWriter();
					StringBuilder sb = proxy.createInjectActivityClass();
					for(String key1: proxyActivityMap.keySet()){
						ClassCreateProxy proxy1 = proxyActivityMap.get(key1);
						sb = proxy1.createInjectActivityClassContent(sb);
					}
					writer.write(proxy.createClassMethodEnd(sb).toString());
					writer.flush();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		if (proxyProviderMap.size() > 0){
			// 开始写Java文件
			for (String key : proxyProviderMap.keySet()) {
				ClassCreateProxy proxy = proxyProviderMap.get(key);
				try {
					JavaFileObject jfo = filer.createSourceFile(proxy.getClassProviderFullName());
					Writer writer = jfo.openWriter();
					StringBuilder sb = proxy.createInjectProviderClass();
					for(String key1: proxyProviderMap.keySet()){
						ClassCreateProxy proxy1 = proxyProviderMap.get(key1);
						sb = proxy1.createInjectProviderClassContent(sb);
					}
					writer.write(proxy.createClassMethodEnd(sb).toString());
					writer.flush();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
				break;
			}
		}
		return false;
	}
}
