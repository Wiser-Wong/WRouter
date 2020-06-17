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

	public String getPath() {
		return path;
	}

	public String getClassName() {
		return className;
	}

	/**
	 * 创建注入Activity类内容
	 *
	 * @return
	 */
	public StringBuilder createInjectActivityClass() {
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(IRouterConstant.PACKAGE_NAME).append(";\n\n");
		sb.append("import com.wiser.router.IRouterActivity;\n" + "import com.wiser.router.WRouter;\n\n");
		sb.append("public class ").append(getClassActivitySimpleName()).append(" implements IRouterActivity {\n\n");
		sb.append("    @Override public void injectActivity() {\n\n");
		return sb;
	}

	/**
	 * 注入Activity
	 * @param sb
	 * @return
	 */
	public StringBuilder createInjectActivityClassContent(StringBuilder sb) {
		sb.append("       WRouter.getInstance().injectActivity(\"").append(path).append("\",").append(className).append(".class);\n\n");
		return sb;
	}

	/**
	 * 创建注入Provider类内容
	 *
	 * @return
	 */
	public StringBuilder createInjectProviderClass() {
		StringBuilder sb = new StringBuilder();
		sb.append("package ").append(IRouterConstant.PACKAGE_NAME).append(";\n\n");
		sb.append("import com.wiser.router.IRouterProvider;\n" + "import com.wiser.router.WRouter;\n\n");
		sb.append("public class ").append(getClassProviderSimpleName()).append(" implements IRouterProvider {\n\n");
		sb.append("    @Override public void injectProvider() {\n\n");
		return sb;
	}

	/**
	 * 注入Provider
	 * @param sb
	 * @return
	 */
	public StringBuilder createInjectProviderClassContent(StringBuilder sb) {
		sb.append("       WRouter.getInstance().injectProvider(\"").append(path).append("\",").append(className).append(".class);\n\n");
		return sb;
	}

	/**
	 * 创建类结尾
	 * @param sb
	 * @return
	 */
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
	public String getClassActivityFullName() {
		return IRouterConstant.PACKAGE_NAME + "." + IRouterConstant.CLASS_ACTIVITY_NAME + "$$" + parseModule(path);
	}

	/**
	 * 获取类全名
	 *
	 * @return
	 */
	public String getClassProviderFullName() {
		return IRouterConstant.PACKAGE_NAME + "." + IRouterConstant.CLASS_PROVIDER_NAME + "$$" + parseModule(path);
	}

	/**
	 * 获取类普通名字
	 * @return
	 */
	private String getClassActivitySimpleName() {
		return IRouterConstant.CLASS_ACTIVITY_NAME + "$$" + parseModule(path);
	}

	/**
	 * 获取类普通名字
	 * @return
	 */
	private String getClassProviderSimpleName() {
		return IRouterConstant.CLASS_PROVIDER_NAME + "$$" + parseModule(path);
	}

}
