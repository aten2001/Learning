
from pylab import *

#create a new figure of size x=8x6 points
# using 80 dots per inch
figure(figsize=(8,6), dpi=80)

#Create new subplot from a grid 1x1
subplot(1,1,1)

X = np.linspace(-np.pi, np.pi, 256, endpoint=True)
C,S = np.cos(X), np.sin(X)

#plot cosine using blue color with
#a continuous line of width 1 pixels
plot(X, C, color="blue", linewidth=2.5,
        linestyle="-", label='cosine')

#similarly fo sine
plot(X, S, color="red", linewidth=2.5,
        linestyle="-", label="sine")

#Note label added to plot to displayed in the legend
# Also below code added to displayed this legend box with those label
legend(loc='upper left')

# below code added to annotate points
t = 2*np.pi/3
#draw dotte line from x to point on curve
plot([t,t],[0,np.cos(t)], color="blue",
        linewidth=2.5, linestyle='--')
#draw a point on the curve - scatter point (as in scatter plot)
scatter([t,],[np.cos(t),],50,color='blue')
#configure annotation text, position
annotate(r'$\cos(\frac{2\pi}{3})=-frac{1}{2}$',
        xy=(t,np.cos(t)), xycoords='data',
        xytext=(-90,-50), textcoords='offset points',
        fontsize=12, arrowprops=dict(arrowstyle="->",
            connectionstyle='arc3,rad=.2'))

xmin, xmax = X.min(), X.max()
ymin = C.min() if C.min() < S.min() else S.min()
ymax = C.max() if C.max() < S.max() else S.max()
dx = (xmax - xmin) * 0.2
dy = (ymax - ymin) * 0.2

#set x limits
xlim(xmin - dx, xmax + dx)

#set x ticks
#xticks(np.linspace(-4, 4, 9, endpoint=True))
#xticks([-np.pi, -np.pi/2, 0, np.pi/2, np.pi])
xticks([-np.pi, -np.pi/2, 0, np.pi/2, np.pi],
        [r'$-\pi$', r'$-\pi/2$', r'$0$', r'$+\pi/2$', r'$+\pi$'])
#set ylim
ylim(ymin - dy, ymax + dy)

#set yticks
#yticks(np.linspace(-1, 1, 5, endpoint=True))
#yticks([-1, 0, +1])
yticks([-1, 0, +1],
        [r'$-1$',r'$0$',r'$+1$'])

#code to move axis
ax = gca() # get spines
 #Hide right and top spines  
ax.spines['right'].set_color('none')
ax.spines['top'].set_color('none')
ax.xaxis.set_ticks_position('bottom')
ax.spines['bottom'].set_position(('data',0))
ax.yaxis.set_ticks_position('left')
ax.spines['left'].set_position(('data',0))

#Added to enhance tick labels
for label in ax.get_xticklabels() + ax.get_yticklabels():
    label.set_fontsize(16)
    label.set_bbox(dict(facecolor='white', edgecolor='None',
        alpha=0.65))
#Save figure using 72 dots per inch
#savefig("test.png", dpi=72)

#show result on screen
show()
