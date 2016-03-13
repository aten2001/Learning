#!/usr/bin/awk -f

BEGIN {
  lines=0;
  total=0;
}
{
  lines++;
  total+=$1;
}
END {
  print lines " lines read";
  print "total is ", total;
  if(lines > 0 ) {
    print " average is ", total/lines;
  }else {
    print "average is 0";
  }
}

