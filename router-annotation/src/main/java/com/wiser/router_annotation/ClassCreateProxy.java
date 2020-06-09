package com.wiser.router_annotation;

/**
 * @author Wiser
 * 
 *         创建类代理
 */
public class ClassCreateProxy {

	private String	className;

	private String	path;

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

	// 创建类方法
	private void createRouterClassMethod(StringBuilder sb) {
		sb.append("   @Override public void injectActivity() {\n");
		sb.append("       WRouter.getInstance().injectActivity(\"").append(path).append("\",").append(className).append(".class);\n");
		sb.append("   }\n");
	}

	/**
	 * 创建类内容
	 *
	 * @return
	 */
	public StringBuilder createClassContent() {
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(IRouterConstant.PACKAGE_NAME).append(";\n\n");
		sb.append("import com.wiser.router.IRouter;\n" + "import com.wiser.router.WRouter;\n\n");
		sb.append("public class ").append(getClassSimpleName()).append(" implements IRouter {\n\n");
		sb.append("    @Override public void injectActivity() {\n\n");
//		sb.append("}");
		return sb;
	}

	// 创建类方法
	public StringBuilder createClassMethod(StringBuilder sb) {
		sb.append("       WRouter.getInstance().injectActivity(\"").append(path).append("\",").append(className).append(".class);\n\n");
		return sb;
	}

	// 创建类方法
	public StringBuilder createClassMethodEnd(StringBuilder sb) {
		sb.append(" 	}\n\n");
		sb.append("}");
		return sb;
	}

	//解析module用于创建类使用
	private String parseModule(String path) {
		if (path == null || path.equals("")) {
			throw new IllegalArgumentException("path must not null!");
		}

		int separatorIndex = path.lastIndexOf("/");
		if (separatorIndex == -1) {
			throw new IllegalStateException("path must has / ");
		}

		return path.substring(0, separatorIndex);
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

}
