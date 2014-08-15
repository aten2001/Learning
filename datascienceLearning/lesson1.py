
from pandas import DataFrame, read_csv

import matplotlib.pyplot as plt
import pandas as pd

names = ['Bob','Jessica','Mary','John','Mel']
births = [968, 155, 77, 578, 973]

BabyDataSet = zip(names,births)

df = DataFrame(data=BabyDataSet, columns=['names','births'])
df.to_csv('births.csv',index=False,header=False)

df = read_csv('births.csv', names=['names','births'])

import os
os.remove('births.csv')

df.dtypes

df.births.dtypes

# appraoches to fetch max
Sorted = df.sort(['births'],ascending=[0])
Sorted.head(1)

df.births.max()

#Present data
df.births.plot()
maxValue = df.births.max()
maxName = df.names[df.births == df.births.max()].values

txt = str(maxValue)+" _ "+maxName

plt.annotate(txt,xy=(1,maxValue),xytext=(8,0),
                xycoords=('axes fraction','data'),
                textcoords='offset points')

print "The most popularName"
print df[df['births'] == df['births'].max()]
plt.show()

