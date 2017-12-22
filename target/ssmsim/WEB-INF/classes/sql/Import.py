#coding=utf-8
import xlrd
import pymysql


if __name__ == '__main__':
    xlsx_path='/home/lucas/PycharmProjects/SIMS/Data/Software_Engineering_Class_3.xls'
    table_name='student'
    wb=xlrd.open_workbook(xlsx_path)
    sh=wb.sheet_by_index(0)
    dfun=[]
    rows=sh.nrows
    cols=sh.ncols
    table_attribute=[]

    table_attribute.append(sh.row_values(0))
    for i in range(1,rows ):
        dfun.append(sh.row_values(i))

    conn=pymysql.connect(host='localhost',user='root',passwd='123517',db='SIMS',port=3306,charset='utf8')
    cursor=conn.cursor()
    Command= 'INSERT INTO {} ({},{},{}) VALUES({},"{}",{})'
    for i in range(0,rows-1):
        cursor.execute(Command.format(table_name,table_attribute[0][0],table_attribute[0][1],table_attribute[0][2],
                       dfun[i][0],dfun[i][1],dfun[i][2]))
    conn.commit()
    conn.close()