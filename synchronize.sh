set -x
set -e
git add .
git status
git commit -m "upt"
git pull gitlab develop
git push gitlab develop
git pull github develop
git push github develop