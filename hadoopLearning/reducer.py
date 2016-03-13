

# Note here there is no map or dictionary used
# as using so would lead to memory of ram being overloaded.
# the crux of shuffle-sort is to avoid this memory load.
# hence results for each key are flushed to files
def redcuer()
  salesTotal = 0
  oldKey = None
    for line in sys.stdin:
      data = line.strip("\t")
      if len(data) != 2:
        continue
    thisKey, thisSale = data

    if oldKey and oldKey != thisKey:
      print "{0}\t{1}".format(oldKey,salesTotal)
      salesTotal = 0

    oldKey = thisKey
    salesTotal += float(thisSale)

  if oldKey != None: # edge case
    print "{0}\t{1}".format(oldKey,salesTotal)
