import cx_Oracle 
'''无视导入错误'''
db=cx_Oracle.connect('coco','coco','localhost:1521/orcl')
print(db.version)
db.close()