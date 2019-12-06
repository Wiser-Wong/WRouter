package com.wiser.router_compiler;

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

import com.google.auto.service.AutoService;
import com.wiser.router_annotation.ClassCreateProxy;
import com.wiser.router_annotation.Router;

@AutoService(Processor.class)
public class RouterProcessor extends AbstractProcessor {

	private Filer							filer;

	private Messager						messager;

	private Map<String, ClassCreateProxy>	proxyMap	= new HashMap<>();

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
			String key = router.value();
			// 获取带有包名的类名
			String activityName = typeElement.getQualifiedName().toString();
			// 获取存储的创建的类对象
			ClassCreateProxy proxy = proxyMap.get(key);
			if (proxy == null) {
				proxy = new ClassCreateProxy(key, activityName);
				proxyMap.put(key, proxy);
			}
		}
		if (proxyMap.size() > 0) {
			// 开始写Java文件
			for (String key : proxyMap.keySet()) {
				ClassCreateProxy proxy = proxyMap.get(key);
				try {
					JavaFileObject jfo = filer.createSourceFile(proxy.getClassFullName());
					Writer writer = jfo.openWriter();
					writer.write(proxy.createClassContent());
					writer.flush();
					writer.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return false;
	}
}
