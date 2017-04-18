'''
Created on 2016.11.08

@author: coco
'''

class Me(object):
    '''
    classdocs
    '''

    def __init__(self):
        '''
        Constructor
        '''
        print("wo le g chacha...");
        
    def love(self): #私有方法带双下划线
        print("boy love the girl...")
        
me = Me();
me.love();
