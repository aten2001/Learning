#!/usr/bin/awk -f
BEGIN {
  print " type a number ";
}
{
  print " the square of ", $1, " is ", $1*$1;
  print " type another number";
}
END {
  print "Done";
}
