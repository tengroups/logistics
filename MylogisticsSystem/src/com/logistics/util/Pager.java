package com.logistics.util;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;
import javax.servlet.jsp.tagext.TagSupport;

/***
 * 分页控件
 * 
 * @author 
 */
public class Pager extends TagSupport {
	private static final long serialVersionUID = 1L;

	//定义当前页页码
	private Integer curPage;
	//定义数据的总行数
	private Integer totalPage;
	//定义每个页面显示多少行数据，这里默认5行
	private Integer pageSize = 5;
	//总的数据行数
	private Integer totalCount = 0;
	//选择提交的表单，用于进行切换，选择另外一页的数据
	private String formId;

	public void setCurPage(Integer curPage) {  
        this.curPage = curPage;  
    }  
  
    public void setPageSize(Integer pageSize) {  
        this.pageSize = pageSize;  
    }  
  
    public void setTotalPage(Integer totalPage) {  
        this.totalPage = totalPage;  
    }  
  
    public void setFormId(String formId) {  
        this.formId = formId;  
    }  
      
    public Integer getTotalCount() {  
        return totalCount;  
    }  
  
    public void setTotalCount(Integer totalCount) {  
        this.totalCount = totalCount;  
    }
    
    public int doStartTag() throws JspException {  
        
        JspWriter out = pageContext.getOut();  
  
        //初始化当前页码为0
        int pageNumber = 0;  
        //首先计算当前获取数据的总行数与页面显示数据的模是否等于0
        /*例如：
         * 我们查询到的数据总行数是15行，每页只显示5行，这时15%5的模是0，可以被整除，则当前页数就是
         * 15/5=3页
         * 如果模不为0，说明有超出的行数，但又不足一页
         * 17/5，则总页数为17/5=3+1=4页         
         */
        if (totalPage%pageSize==0) {  
            pageNumber = totalPage/pageSize;  
        } else {  
            pageNumber = (totalPage/pageSize)+1;  
        }  
        //完善判断，点击上一页时，如果当前页为第一页，点上一页，则页码会是0，小于1，系统会报错，所以默认还是1
        if (curPage < 1) {  
            curPage = 1;  
        }  
  
        try {  
        	//如当前页码大于0，则输出脚本，脚本功能为选择某一页，根据选择的页码而定
            if (pageNumber > 0) {  
                out.print("<script type='text/javascript'>" +   
                                "function go(pageNum){" +   
                                    "var f = document.getElementById('" + formId + "');"+  
                                    "f.action = f.action + '?pageNum=' + pageNum + '&pageSize="+pageSize+"';"+  
                                    "f.submit();"+  
                                "}" +   
                          "</script>");  
                  
                out.print("<div class='mypager'><ul class='pagination'>");  
                //起始页为1
                int start = 1;  
                //结束页为上面计算的总页数
                int end = totalPage;  
                //显示可选页码为4位，例如： 上一页  1 2 3 4 下一页
                for(int i=4;i>=1;i--){  
                    if((curPage-i)>=1){  
                        start = curPage-i;  
                        break;  
                    }  
                }  
                for(int i=4;i>=1;i--){  
                    if((curPage+i)<=totalPage){  
                        end = curPage+i;  
                        break;  
                    }  
                }  
                //如果小于9则右侧补齐  
                if(end-start+1<=9){  
                    Integer padLen = 9-(end-start+1);  
                    for(int i=padLen;i>=1;i--){  
                        if((end+i)<=totalPage){  
                            end = end+i;  
                            break;  
                        }  
                    }  
                }  
                  
                //如果还小于9左侧补齐  
                if(end-start+1<=9){  
                    Integer padLen = 9-(end-start+1);  
                    for(int i=padLen;i>=1;i--){  
                        if((start-i)>=1){  
                            start = start-i;  
                            break;  
                        }  
                    }  
                }  
                
                if(curPage==1){
                	out.print("<li><a href='javascript:go(1)'>首页</a></li>");
                }
                
                if(curPage>1){  
                    if(start>1){  
                        out.print("<li><a href='javascript:go(1)'>首页</a></li>");  
                    }  
                    out.print("<li><a href='javascript:go("+(curPage-1)+")'>上一页</a></li>");  
                }  
                
                //循环将页面遍历添加到div中
                for(int i=start;i<=end;i++){  
                    if(i==curPage){  
                        out.print("<li class='active'><a>" + i + "</a></li>");  
                    }else{  
                        out.print("<li><a href='javascript:go("+i+")'>" + i + "</a></li>");  
                    }  
                }  
                if(curPage<totalPage){  
                    out.print("<li><a href='javascript:go("+(curPage+1)+")'>下一页</a></li>");  
                    if(end<totalPage){  
                        out.print("<li><a href='javascript:go("+totalPage+")'>尾页</a></li>");  
                    }  
                }
                if(curPage==totalPage){
                	out.print("<li><a href='javascript:go("+totalPage+")'>尾页</a></li>");
                }
                out.print("<li><a href='javascript:void(0)'>共" + totalPage + "页" + this.totalCount + "条</a></li>");  
                out.print("</ul></div>");  
            }  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
  
        return super.doStartTag();  
  
    }  
      
    public static Integer getStartIndex(Integer pageNum, Integer pageSize){  
        Integer res = 0;  
        if(pageNum>0){  
            res = (pageNum-1)*pageSize;  
        }  
        return res;  
    } 
}
