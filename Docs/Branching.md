## Walmarket Branching Strategy
Below summarizes the branching strategy employed for maintaining a cohesive codebase.

### General Contributing
---
At the begining of any project task, a corresponding issue must be created and/or assigned along with time estimation for the scope of the problem at hand. Upon completion, a log of time spent must also be submitted to the issue on GitLab in order to improve future estimation abilities.

### Branching Strategy Overview
---
Our group has been employing a version of Git Flow as its branching strategy. The purpose of Git Flow is to maintain a "pure" main branch for the purpose of releases only and have all iterative work be split off from there in increasingly specialized branches for features, user-stories, and dev-tasks as appropriate. Importantly, any branch beyond the dev-task level must be kept in a state of "working" (ie. no failed unit tests, and no compilation errors) in order to be usable by other members of the team. Branch naming convention is relatively lax, simply requiring a prefix of feature_, userstory_, or devtask_ respectively.

### Merge Requests
---
Due to project scale, merge requests are left to be approved by the one submitting them. Titles of merge requests should concisely explain their purpose and descriptions should summarize the additions to the recipient branch. Branches need not be closed following a merge request unless all relevent work is completed on the source branch. Inactive branches should not be maintained in the codebase.
