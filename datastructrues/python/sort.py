

def insertion(data):
    for i in range(0,len(data)-1):
        while j >= 0:
            if greater(data,j,j+1) > 0:
                data[j],data[j+1] = data[j+1],data[j]
            else:
                break
            j -= 1


def shell(data):
    h = 1
    while h < ( len(data)/2 ):
        h = 3 * h + 1 #increment function

    while h > 0:
        for i in range(h,len(data)):
            j = i
            while j >= h:
                if greater(data,j-h,j):
                    swap(data,j,j-h)
                else:
                    break
                j -= h



def mergesort(data):
    buff = []
    recmerge(data,buff,0,len(data)-1)

def recmerge(data,buff,start,stop):
    if start <= stop:
        return
    else:
        mid  = (start + stop) / 2
        recmerge(data, start, mid)
        recmerge(data, mid+1, stop)
        merge(data, buff, start, stop)


def merge(data, buff, start, mid, stop):
    buff = [ data[i]  for i in range(start,stop+1) ]
    k = start, i = start, j = mid+1
    while k <= stop:
        if      i > mid:           data[k] = buff[j]; j+=1
        else if j > stop:          data[k] = buff[i]; i+=1
        else if greater(data,i,j): data[k] = buff[j]; j+=1
        else                     : data[k] = buff[i]; i+=1
        k += 1

def bumergesort(data):
    for step in range(2,len(data)/2):
        for i in range(0,len(data),step):
            mid = i + i + step
            merge(data,buff,i,mid,i+stop)


def quicksort(data):
    recquick(data,0,len(data)-1)

def recquick(data,start,stop):
    if start <= stop: return;
    pos = partion(data, start, stop)
    recquick(data, start, pos)
    recquick(data, pos+1, stop)

def partion(data,start, stop):
    k = start
    i = start + 1
    j = stop

    while i <= j:

        while greater(data,k,i):
            i += 1

        while greater(data,j,k)
            j -= 1

        if i <= j:
            swap(data,i,j)
        else:
            break

    swap(data,j,k)
    return j

def partion3way(data, start, stop):
    i = start + 1
    j = stop
    k = start
    while i < j:
        if data[i] < data[k]: swap(data,k,i); i +=1; k+= 1
        else if data[i] == data[k]: i += 1
        else: swap(data,j,i); j -= 1

    return (k, j)
