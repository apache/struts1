#!/bin/bash


cd /home/jmitchell/svn/STRUTS_1_2_BRANCH

ant clean > nightly.log; svn status >> nightly.log; ant download-dependencies nightly >> nightly.log & tail -f nightly.log

scp /home/jmitchell/svn/STRUTS_1_2_BRANCH/release/upload/*bin* cvs.apache.org:/www/cvs.apache.org/builds/jakarta-struts/maven/STRUTS_1_2_BRANCH/nightly/binary >> nightly.log
scp /home/jmitchell/svn/STRUTS_1_2_BRANCH/release/upload/*lib* cvs.apache.org:/www/cvs.apache.org/builds/jakarta-struts/maven/STRUTS_1_2_BRANCH/nightly/lib >> nightly.log
scp /home/jmitchell/svn/STRUTS_1_2_BRANCH/release/upload/*src* cvs.apache.org:/www/cvs.apache.org/builds/jakarta-struts/maven/STRUTS_1_2_BRANCH/nightly/src >> nightly.log
scp nightly.log /www/cvs.apache.org/builds/jakarta-struts/maven/STRUTS_1_2_BRANCH/nightly/logs
