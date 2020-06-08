package com.wiser.router_annotation;

import java.util.HashMap;
import java.util.Map;

/**
 * @author Wiser
 * 
 *         创建类代理
 */
public class ClassCreateProxy {

	private String	className;

	private String	path;

	private Map<String,String> rootMap = new HashMap<>();

	public ClassCreateProxy(String path, String className) {
		this.path = path;
		this.className = className;
	}

	/**
	 * 创建类内容
	 * 
	 * @return
	 */
	public String createRouterClassContent() {
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(IRouterConstant.PACKAGE_NAME).append(";\n\n");
		sb.append("import com.wiser.router.IRouter;\n" + "import com.wiser.router.WRouter;\n");
		sb.append("public class ").append(getClassSimpleName()).append(" implements IRouter {\n");
		// 加入方法
		createRouterClassMethod(sb);
		sb.append("}");
		return sb.toString();
	}

	/**
	 * 创建类内容
	 *
	 * @return
	 */
	public String createMapClassContent() {
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(IRouterConstant.PACKAGE_NAME).append(";\n\n");
		sb.append("import com.wiser.router.IRoot;\n" + "import com.wiser.router.WRouter;\n");
		sb.append("public class ").append(getClassRootSimpleName()).append(" implements IRoot {\n");
		// 加入方法
		createMapClassMethod(sb);
		sb.append("}");
		return sb.toString();
	}

	// 创建类方法
	private void createRouterClassMethod(StringBuilder sb) {
		sb.append("   @Override public void injectActivity() {\n");
		sb.append("       WRouter.getInstance().injectActivity(\"").append(path).append("\",").append(className).append(".class);\n");
		sb.append("   }\n");
	}

	// 创建类方法
	private void createMapClassMethod(StringBuilder sb) {
		sb.append("   @Override public void putModule() {\n");
		sb.append("       WRouter.getInstance().putModule(\"").append(path).append("\",").append("\"").append(className).append("\");\n");
		sb.append("   }\n");
	}

	//解析module用于创建类使用
	private String parseModule(String path) {
		if (path == null || path.equals("")) {
			throw new IllegalArgumentException("path must not null!");
		}

		int separatorIndex = path.indexOf("/");
		if (separatorIndex == -1) {
			throw new IllegalStateException("path must has / ");
		}

		String moduleName = path.substring(0, separatorIndex);

        return moduleName;
	}

	/**
	 * 获取类全名
	 * 
	 * @return
	 */
	public String getClassFullName() {
		return IRouterConstant.PACKAGE_NAME + "." + IRouterConstant.CLASS_NAME + "$$" + parseModule(path);
	}

	/**
	 * 获取类普通名字
	 * @return
	 */
	private String getClassSimpleName() {
		return IRouterConstant.CLASS_NAME + "$$" + parseModule(path);
	}

	/**
	 * 获取类全名
	 *
	 * @return
	 */
	public String getClassRootFullName() {
		return IRouterConstant.PACKAGE_NAME + "." + IRouterConstant.ROOT_CLASS_NAME + "$$" + parseModule(path);
	}

	/**
	 * 获取类普通名字
	 * @return
	 */
	private String getClassRootSimpleName() {
		return IRouterConstant.ROOT_CLASS_NAME + "$$" + parseModule(path);
	}

}
