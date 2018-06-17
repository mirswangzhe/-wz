<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<div>
	 <a class="easyui-linkbutton" onclick="inputIndex()">一键导入索引库</a>
</div>

<script type="text/javascript">
	function inputIndex() {
		$.post("input/index",null,function(){
			$.messager.alert('提示','导入索引库成功');
		});
	}
</script>