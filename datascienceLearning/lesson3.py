

from pandas import DataFrame, date_range, read_excel, concat
import pandas as pd
import matplotlib.pyplot as plt
import numpy.random as np
import sys

#set seed
np.seed(111)

def createDataSet(number=1):

    output = []
    for i in range(number):
        #Weekely mondays date range
        rng = date_range(start='1/1/2009', end='12/31/2012', freq='W-MON')
        #print "Weekly mondays date range"
        #print rng[:10]

        #create random data
        # pick random number from 25 to 1000 for len(rng) times
        data = np.randint(low=25,high=1000,size=len(rng))

        #status pool
        status = [1,2,3]

        #random status generation
        random_status= [ status[np.randint(low=0,high=len(status))] for i in range(len(rng))]

        #Note above two methods used in generating random data
        #and radom status yield same results and can be used
        #interchangebly

        states = ['GA','FL','fl','NY','NJ','TX']

        random_states = [ states[np.randint(low=0, high=len(states))] for i in range(len(rng))]

        output.extend(zip(random_states,random_status,data,rng))

    return output


dataset = createDataSet(4)
df = DataFrame(data=dataset, columns=['state','status','count','date'])
print "df.info()"
print df.info()

print"df.head()"
print df.head()

# note only index=False is given.. which implies header is saved
df.to_excel('buff.xlsx', index=False)
print 'saved to excel'

# read excel - sheet path, sheet no and date col is made as index
df = read_excel('buff.xlsx',0, index_col='date')
print "df.dtypes"
print df.dtypes

print "df.index"
print df.index

print df.head()

import os
os.remove("buff.xlsx")

#Prepare data
# 1) make state col all in upper case
# 2) select recrods only with status = 1
# 3) merge NJ and NY to NY
# 4) Remove odd results in dataset

print df.state.unique()
df.state = df.state.apply(lambda x: x.upper())
print df.state.unique()

mask = df.status == 1
df = df[mask]

mask = df.state == 'NJ'
df.state[mask] = 'NY'

print df.state.unique()

#df['count'].plot();

# to group by state and date we need to reset_index
# rest_index is done to be able to group by date
# as group by expects columns and not index columns
daily = df.reset_index().groupby(['state','date']).sum()
print daily.head()

# note in group by.. the output dataframe index columns are automatically
# state and date
# columns as index allows easy selection of plots and perform calculations on data

#Delete status column as we dont need it post filtering
del daily['status']

daily.head()

daily.index
# daily.index has levels [] -- with entries of state and date levels[0] and levels[1] respectively.. it also has labels for state and date.. llabels are ususally running seq numbers


# ploting data per state. 
# to refer to index use loc

daily.loc['FL'].plot()
daily.loc['GA'].plot()
daily.loc['NY'].plot()
daily.loc['TX'].plot()
plt.show();
