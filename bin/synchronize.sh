set -x
set -e
git add .
git status
git commit -m "upt"
git pull origin develop
git push origin develop