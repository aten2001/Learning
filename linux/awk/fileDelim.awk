#!/usr/bin/awk -f

BEGIN {
  FS=":";
}
{
  if( $1 != "#" &&  $2 == "" ) {
    print $1 ": no password";
  }
}
