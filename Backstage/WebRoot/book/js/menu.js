$(function(){
    //刷新
    $("#m-refresh").click(function(){
        var currTab = $('#tabs').tabs('getSelected');    //获取选中的标签项
        var url = $(currTab.panel('options').content).attr('src');    //获取该选项卡中内容标签（iframe）的 src 属性
        /* 重新设置该标签 */
        $('#tabs').tabs('update',{
            tab:currTab,
            options:{
                content: createTabContent(url)
            }
        })
    });
    
    //关闭所有
    $("#m-closeall").click(function(){
        $(".tabs li").each(function(i, n){
            var title = $(n).text();
            $('#tabs').tabs('close',title);    
        });
    });
    
    //除当前之外关闭所有
    $("#m-closeother").click(function(){
        var currTab = $('#tabs').tabs('getSelected');
        currTitle = currTab.panel('options').title;    
        
        $(".tabs li").each(function(i, n){
            var title = $(n).text();
            
            if(currTitle != title){
                $('#tabs').tabs('close',title);            
            }
        });
    });
    
    //关闭当前
    $("#m-close").click(function(){
        var currTab = $('#tabs').tabs('getSelected');
        currTitle = currTab.panel('options').title;    
        $('#tabs').tabs('close', currTitle);
    });
});

$(function(){
     /*为选项卡绑定右键*/
    $(".tabs li").live('contextmenu',function(e){
        
        /* 选中当前触发事件的选项卡 */
        var subtitle =$(this).text();
        $('#tabs').tabs('select',subtitle);
        
        //显示快捷菜单
        $('#menu').menu('show', {
            left: e.pageX,
            top: e.pageY
        });
        
        return false;
    });
});