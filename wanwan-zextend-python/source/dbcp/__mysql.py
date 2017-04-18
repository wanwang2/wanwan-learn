import pymysql
conn = pymysql.connect(user='root', passwd='root',
                 host='localhost', db='me')
cur = conn.cursor()
cur.execute("SELECT * FROM user")
for r in cur:      
      print("row_number:" , (cur.rownumber) )        
      print("id:"+str(r[0])+"name:"+str(r[1])+" password:"+str(r[2])) 
cur.close()    
conn.close()
