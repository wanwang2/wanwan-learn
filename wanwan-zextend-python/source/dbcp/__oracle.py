import cx_Oracle 
'''���ӵ������'''
db=cx_Oracle.connect('coco','coco','localhost:1521/orcl')
print(db.version)
db.close()