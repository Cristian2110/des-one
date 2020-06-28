import groovy.json.JsonOutput

node('nodo-peru-claro') {

	/********************************************************************************************************************************/
	/** Herramientas **/

	/** Project **/
	def job_split                       = env.JOB_NAME.split('/')
	def country_name                    = job_split[0]
	def consumer_name                   = job_split[1]
	def consumer_name_upper             = consumer_name.toUpperCase()
	def app_name                        = job_split[3]
	def sq_project_name                 = "${country_name}.${consumer_name_upper}.${app_name}"
	def sq_project_version              = new Date().format('yyyyMMddhhmmss')
	def path_workspace_project          = "C:\\${country_name}\\${consumer_name}\\${app_name}\\${branch_name}"

	/** Workspace **/
	def branch_name = env.BRANCH_NAME

	env.GIT_URL                         = "https://steps.everis.com/git/${country_name}${consumer_name_upper}/${app_name.toLowerCase()}.git"
	env.GIT_COMMIT_DESC                 = ""
	env.context                         = ""

	/*** Mails **/
	def recipients = 'mreynahu@everis.com'

	/********************************************************************************************************************************/
	try {
		checkout(path_workspace_project, branch_name)
		build(path_workspace_project)
		unit_test(path_workspace_project)
		code_coverage(path_workspace_project)
		code_analysis(path_workspace_project, sq_project_name, sq_project_version, branch_name)
	} catch (any) {
		currentBuild.result				= "FAILURE"
		throw any
	} finally {
		step([
			$class						: "Mailer",
			notifyEveryUnstableBuild	: true,
			recipients					: recipients
			, sendToIndividuals			: true
		])
	}
}

/**** METODOS **/
def checkout (path_workspace_project, branch_name) {
	config_stage("checkout", "git")

	bat "if not exist \"${path_workspace_project}\" mkdir ${path_workspace_project}"

	dir(path_workspace_project) {
        checkout([
			$class						: 'GitSCM', 
			branches					: [[
				name					: '*/'+branch_name
			]],
			doGenerateSubmoduleConfigurations: false, 
			extensions					: [],
			submoduleCfg				: [],
			userRemoteConfigs			: [[
				credentialsId			: '3ddc4efe-42a3-4202-9a32-5943e4905f1c', 
				url						: env.GIT_URL
			]]
		])
    }
}

def build (path_workspace_project) {
	config_stage("build", "maven")

	dir(path_workspace_project) {
		bat                         	"mvn clean install -DskipTests -U"
		//bat "mvn dependency:purge-local-repository clean install -U"
	}
}

def unit_test (path_workspace_project){
	config_stage("test", "maven")

	dir(path_workspace_project){
		bat                         	"mvn test"
		junit                       	".reports-surefire/*.xml"
	}
}

def code_coverage (path_workspace_project){
	config_stage("code-coverage", "jacoco")

	dir (path_workspace_project){
		jacoco(
		    execPattern					: "**/target/jacoco.exec",
		    classPattern				: "**/target/classes",
		    sourcePattern				: "**/src/main",
		    exclusionPattern			: ".reports-jacoco/**,.reports-surefire/**"
        )
        
        publishHTML([
            allowMissing				: false,
            alwaysLinkToLastBuild		: false,
            keepAll						: false,
            reportDir					: ".reports-jacoco/",
            reportFiles					: "index.html",
            reportName					: "Reporte de Cobertura",
            reportTitles				: ""
        ])
	}
}

def code_analysis(path_workspace_project, sq_project_name, sq_project_version, branch_name) {
	config_stage("code-analysis", "sonar-way")
	
    def sonar_scanner               	= tool name: 'SonarQube 3.0.3.778', type: 'hudson.plugins.sonar.SonarRunnerInstallation'
	def branch_sonar                	= ""
	
	if (branch_name != "master") {
	    branch_sonar = "branch-${branch_name}"
	    
	} else {
	    branch_sonar = "${branch_name}"
	}
	
    withSonarQubeEnv('SonarQube-IT&S-Enterprise') {
        dir(path_workspace_project) {
            bat "${sonar_scanner}/bin/sonar-scanner -X" +
            " -Dsonar.host.url=%SONAR_HOST_URL%" +
            " -Dsonar.login=eee764982a8e7ca52cc9757819d88529aab4dd86" +
            " -Dsonar.projectKey=" + sq_project_name +
            " -Dsonar.projectName=" + sq_project_name +
            " -Dsonar.projectVersion=" + sq_project_version +
            " -Dsonar.branch.name=${branch_sonar}" +
            " -Dsonar.profile=\"Reglas Claro Java V1\"" +
            " -Dsonar.language=\"java\"" +
            " -Dsonar.sources=\".\"" +
			" -Dsonar.projectBaseDir=\".\"" +
			" -Dsonar.java.binaries=\"**/target/classes\"" +
            " -Dsonar.exclusions=\"**/src/test,.reports-jacoco,.reports-surefire\",**/claro-common/**,**/claro-rest-api-common/**,**/claro-rest-api-client/**" +
            " -Dsonar.junit.reportPaths=\".reports-surefire\"" +
            " -Dsonar.java.coveragePlugin=\"jacoco\"" +
            " -Dsonar.jacoco.xmlReportPaths=\".reports-jacoco/jacoco.xml\"" +
            " -Dsonar.sourceEncoding=\"UTF-8\""
        }
    }
}

def config_stage (stage_name, tool_name) {
	def stage_value = "${stage_name}:${tool_name}"
	stage stage_value
	env.context = "stage : ${stage_value}"
}
