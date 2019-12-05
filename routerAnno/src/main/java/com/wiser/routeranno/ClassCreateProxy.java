package com.wiser.routeranno;

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
	public String createClassContent() {
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(getClassFullName()).append(";\n\n");
		sb.append("import com.wiser.router.IRouter;\n" + "import com.wiser.router.WRouter;\n");
		sb.append("public class ").append(IRouterConstant.CLASS_NAME).append(" implements IRouter {\n");
		// 加入方法
		createClassMethod(sb);
		sb.append("}");
		return sb.toString();
	}

	// 创建类方法
	private void createClassMethod(StringBuilder sb) {
		sb.append("   @Override public void injectActivity() {\n");
		sb.append("       WRouter.getInstance().injectActivity(\"").append(path).append("\",").append(className).append(".class);\n");
		sb.append("   }\n");
	}

	/**
	 * 获取类全名
	 * 
	 * @return
	 */
	public String getClassFullName() {
		return IRouterConstant.PACKAGE_NAME + "." + IRouterConstant.CLASS_NAME;
	}

}
