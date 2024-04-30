import os

def generate_negative_description_file2():
    
    with open('neg2.txt','w') as f:
        
        for filename in os.listdir('negative2'):
            f.write('negative/' + filename + '\n')
            
            
def gen_neg_desc_file_IHCI():
    
    with open('neg.txt','a+') as f :
        lines=f.readlines()
        
        for filename in os.listdir('negative'):
            if not filename in lines :
                f.append(filename)

   