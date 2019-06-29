# Yahoo Finance Parser

Kotlin project to parse Yahoo Finance for fundamental (financial statements) data across a universe of US equities. This project is used in conjunction with the financial-statement server to show how to build micro-services with self-provided data lifecycle

The parsed data is transformed and cleaned into financial statement objects, these objects are then passed to a live CRUD server via the [raincove-sdk](https://github.com/raincove-io/raincove-sdk/)

# Deployment

[GitOps](https://www.weave.works/technologies/gitops/) allow us to define an application as a set of Kubernetes objects. Therefore this CLI program (when built as a Docker image) can be deployed as a [CronJob](https://kubernetes.io/docs/concepts/workloads/controllers/cron-jobs/) that is deployed along side a statefulset database front-end by a horizontally scalable CRUD server