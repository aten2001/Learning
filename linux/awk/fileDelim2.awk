#!/usr/bin/awk -f
{
  if( $1 =="#START" ) {
    FS=":";
  }else if ( $1 == "#STOP" ){
    FS=" ";
  }else {
    print $3
  }
}

