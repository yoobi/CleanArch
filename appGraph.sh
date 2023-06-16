#!/bin/bash

./gradlew generateModulesGraphvizText -Pmodules.graph.output.gv=project_graph
if [[ -f project_graph ]]; then
  dot -Tpng project_graph -O
  rm project_graph
  git add project_graph.png
fi
exit 0