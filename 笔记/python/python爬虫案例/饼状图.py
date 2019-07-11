
from matplotlib import pyplot as plt  
labels   = [102,222]  #标签
quants   = [2,2]        #这个是数据  
labels.append(104)
quants.append(32)
    
def explode(label, target='China'):
    return 0
print(type(explode))
expl = list(map(explode,labels)) 
colors  = ["yellow","orange","pink","coral"]  
plt.pie(quants, explode=expl, colors=colors, labels=labels, autopct='%1.1f%%',pctdistance=0.8, shadow=True)  
plt.show() yy
