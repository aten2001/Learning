#!/usr/bin/awk -f
BEGIN {
  OFS=",";
}
{
  $1="";
  $3="";
  print;
}
