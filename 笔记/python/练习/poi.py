from pyecharts import Pie

attr = list(1)
v1 = list(2)
pie = Pie('POI分布情况')
pie.add("", attr, v1, is_label_show=True, radius=[30, 75])
pie
