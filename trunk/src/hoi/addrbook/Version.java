package hoi.addrbook;

/**
 * Google: <br>
 * 软件 版本 规则 / 软件 版本 规范
 * 
 * @author Hoi
 */
public class Version {
	/**
	 * 第一位为主版本号，在程序进行重大变更，如实现技术发生改变，或者变更巨大的时候才会增长；
	 */
	private static final int MAJOR_VERSION_NUMBER = 0;
	/**
	 * 第二位为次版本号，在程序变更比较大的时候，如变更导致部分程序实现发生改变时才会增长；
	 */
	private static final int MINOR_VERSION_NUMBER = 0;
	/**
	 * 第三位为发行版本号，每发行一次增加1，标志软件发行次数。当主版本和次版本增长后，发行版本会归零；
	 */
	private static final int REVISION_NUMBER = 1;
	/**
	 * 第四位为编译版本号，属于内部的小版本号，只是简单的标志编译次数，对客户表现为无序增长。
	 */
	private static final int BUILD_NUMBER = -1; // SVN VERSION

	//	private static final String STAGE = new String[] {
	//			"Base", "Alpha", "Beta", "RC", "Release" }[2];
	private static final String LANGUAGE = new String[] {
			"CN", "EN" }[0];

	public static String FULL_VERSION = String.format("%d.%d.%d.%d_%s", //
			MAJOR_VERSION_NUMBER, MINOR_VERSION_NUMBER, REVISION_NUMBER, BUILD_NUMBER, LANGUAGE);
	public static String SHORT_VERSION = String.format("%d.%d.%d", //
			MAJOR_VERSION_NUMBER, MINOR_VERSION_NUMBER, REVISION_NUMBER);
}
