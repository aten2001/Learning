
from pandas import DataFrame, read_csv
from numpy import random

import matplotlib.pyplot as plt
import pandas as pd
import sys


names = ["Bob","Jessica","Mary","John","Mel"]

random.seed(500) #Create seed
random_names = [ names[random.randint(low=0, high = len(names))] for i in range(1000) ]
births = [ random.randint(low=0, high=1000) for i in range(1000) ]

#print random_names[:10]
#print births[:10]

dataset = zip(random_names,births)

df = DataFrame(data=dataset, columns=['Names','Births'])
#print df[:10]

df.to_csv("births1880.txt",index=False,header=False)

df = read_csv(r'./births1880.txt',names=["Names","Births"])
print "df.info over all info of df"
print df.info()
print "df.head - first 5 rows"
print df.head()
import os
os.remove(r'./births1880.txt')

uqNames = df['Names'].unique()

print "df['names'].unique()"
print uqNames

print "df.names.describe()"
print df['Names'].describe()

df = df.groupby("Names")  #group by name
print df
df = df.sum() # applys sum to each groupBy obj
print df
#above is equivalent os select sum(births) from df group by names;


Sorted = df.sort(columns="Births", ascending=False)
print Sorted.head(1)

#or
df['Births'].max()

#Create Graph
df['Births'].plot(kind="bar")

print "The most popular name"
df.sort(columns='Births', ascending=False)

plt.show()

