apiVersion: v1
kind: BuildConfig
metadata:
  annotations:
    pipeline.alpha.openshift.io/uses: '[{"name": "tasks", "namespace": "", "kind": "DeploymentConfig"}]'
  labels:
    name: tasks-pipeline
  name: tasks-pipeline
spec:
  strategy:
    jenkinsPipelineStrategy:
      jenkinsfile: |-
        node('maven') {
            stage 'build'
            openshiftBuild(buildConfig: 'tasks', showBuildLogs: 'true')

            stage 'deploy'
            openshiftDeploy(deploymentConfig: 'tasks')
        }
    type: JenkinsPipeline
  triggers:
  - github:
      secret: CzgPZAZ5m2
    type: GitHub
  - generic:
      secret: CzgPZAZ5m2
    type: Generic
