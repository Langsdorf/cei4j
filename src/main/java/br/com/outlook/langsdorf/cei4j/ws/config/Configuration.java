package br.com.outlook.langsdorf.cei4j.ws.config;

public class Configuration {

	public static String MAIN_URL = "https://cei.b3.com.br/CEI_Responsivo/";
	public static String STOCKS_URL = "https://cei.b3.com.br/CEI_Responsivo/ConsultarCarteiraAtivos.aspx";
	public static String STOCKS_TRADES_URL = "https://cei.b3.com.br/CEI_Responsivo/negociacao-de-ativos.aspx";

	public static String NAME_INPUT_PATH = "ctl00$ContentPlaceHolder1$txtLogin";
	public static String PASSOWORD_INPUT_PATH = "ctl00$ContentPlaceHolder1$txtSenha";
	public static String LOGIN_BUTTON_PATH = "ctl00$ContentPlaceHolder1$btnLogar";
	public static String FULLNAME_ID_PATH = "ctl00_lblNome";
	public static String STOCK_VIEW_BUTTON_PATH = "ctl00_ContentPlaceHolder1_btnConsultar";
	public static String TABLES_PATH = "//*[contains(@id, '_lblAgenteContas') and not(contains(@id, 'rptContaMercado'))]";
	public static String STOCKS_PATH = "//*[@id='ctl00_ContentPlaceHolder1_rptAgenteContaMercado_ctl0" + "$i$"
			+ "_rptContaMercado_ctl00_rprCarteira_ctl00_grdCarteira']/thead/tr[not(position()=1)] | //*[@id='ctl00_ContentPlaceHolder1_rptAgenteContaMercado_ctl0"
			+ "$i$" + "_rptContaMercado_ctl00_rprCarteira_ctl00_grdCarteira']/tbody";
	public static String STOCKS_TRADES_SELECT_PATH = "ctl00$ContentPlaceHolder1$ddlAgentes";
	public static String STOCKS_TRADES_BUTTON_PATH = "ctl00_ContentPlaceHolder1_btnConsultar";
	public static String STOCKS_TRADES_TABLE_PATH = "//*[@id='ctl00_ContentPlaceHolder1_rptAgenteBolsa_ctl00_rptContaBolsa_ctl00_pnResumoNegocios']/div/div/section/div/table/thead/tr[not(position()=1)] | //*[@id='ctl00_ContentPlaceHolder1_rptAgenteBolsa_ctl00_rptContaBolsa_ctl00_pnResumoNegocios']/div/div/section/div/table/tbody";

	public static String CHROMEDRIVER_PATH = System.getenv("CHROMEDRIVER_PATH");
	// "D:/webdriver/chromedriver.exe";
	// System.getenv("CHROMEDRIVER_PATH");
	public static int TIMEOUT = 60;

}
