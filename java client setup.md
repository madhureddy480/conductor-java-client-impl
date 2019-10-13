# java Client Setup - Learning through an example.

Expectation is Conductor server and UI apps are up and running. If not true, follow 


Step 1: Task Definitions
```
curl -X POST \
  http://localhost:8080/api/metadata/taskdefs \
  -H 'Content-Type: application/json' \
  -d '[
    {
      "name": "verify_if_idents_are_added",
      "retryCount": 3,
      "retryLogic": "FIXED",
      "retryDelaySeconds": 10,
      "timeoutSeconds": 300,
      "timeoutPolicy": "TIME_OUT_WF",
      "responseTimeoutSeconds": 180
    },
    {
      "name": "add_idents",
      "retryCount": 3,
      "retryLogic": "FIXED",
      "retryDelaySeconds": 10,
      "timeoutSeconds": 300,
      "timeoutPolicy": "TIME_OUT_WF",
      "responseTimeoutSeconds": 180
    }
]'
```
Step 2: Workflow Definition with above tasks.

```
curl -X POST \
  http://localhost:8080/api/metadata/workflow \
  -H 'Content-Type: application/json' \
  -d '{
    "name": "add_netflix_identation",
    "description": "Adds Netflix Identation to video files.",
    "version": 2,
    "schemaVersion": 2,
    "tasks": [
        {
            "name": "verify_if_idents_are_added",
            "taskReferenceName": "ident_verification",
            "inputParameters": {
                "contentId": "${workflow.input.contentId}"
            },
            "type": "SIMPLE"
        },
        {
            "name": "decide_task",
            "taskReferenceName": "is_idents_added",
            "inputParameters": {
                "case_value_param": "${ident_verification.output.is_idents_added}"
            },
            "type": "DECISION",
            "caseValueParam": "case_value_param",
            "decisionCases": {
                "false": [
                    {
                        "name": "add_idents",
                        "taskReferenceName": "add_idents_by_type",
                        "inputParameters": {
                            "identType": "${workflow.input.identType}",
                            "contentId": "${workflow.input.contentId}"
                        },
                        "type": "SIMPLE"
                    }
                ]
            }
        }
    ]
}'
```

clone/download the source code and send a create workflow request like below to see conductor working. 

```
curl -X POST \
  http://localhost:8080/api/workflow/add_netflix_identation \
  -H 'Content-Type: application/json' \
  -d '{
    "identType": "animation",
    "contentId": "my_unique_content_id"
}'

```

