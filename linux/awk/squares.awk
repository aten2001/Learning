#!/usr/bin/awk -f

BEGIN {
  i=1;
  while (i <= 10) {
    print " The square of", i, " is ", i*i;
    i++;
  }

  for (i=1; i <= 10; i++) {
    print "the square of", i," is ", i*i;
  }

exit;
}
