#!/bin/bash


cd /home/jmitchell/svn/STRUTS_1_2_BRANCH

export TODAY=`date +%Y%m%d`
export LOGFILE=/home/jmitchell/svn/STRUTS_1_2_BRANCH/nightly-$TODAY.log
ant clean-lib > $LOGFILE
svn status >> $LOGFILE 
ant download-dependencies nightly >> $LOGFILE

scp /home/jmitchell/svn/STRUTS_1_2_BRANCH/release/upload/*bin* cvs.apache.org:/www/cvs.apache.org/builds/jakarta-struts/maven/STRUTS_1_2_BRANCH/nightly/binary >> $LOGFILE
scp /home/jmitchell/svn/STRUTS_1_2_BRANCH/release/upload/*lib* cvs.apache.org:/www/cvs.apache.org/builds/jakarta-struts/maven/STRUTS_1_2_BRANCH/nightly/lib >> $LOGFILE
scp /home/jmitchell/svn/STRUTS_1_2_BRANCH/release/upload/*src* cvs.apache.org:/www/cvs.apache.org/builds/jakarta-struts/maven/STRUTS_1_2_BRANCH/nightly/src >> $LOGFILE
scp $LOGFILE cvs.apache.org:/www/cvs.apache.org/builds/jakarta-struts/maven/STRUTS_1_2_BRANCH/nightly/logs



