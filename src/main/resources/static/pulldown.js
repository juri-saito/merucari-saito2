
/**
 * 中プルダウン連動のためのjquery
 */
$(function(){
		
	/**
	 * 中プルダウン連動のためのjquery
	 */
	$("#parentCategory").change(function(){
		//プルダウンで選択された値を取得
		var value = $("#parentCategory option:selected").val();
		//jQuery.get(url, data)　
		//第一引数：コントローラに送るためのパス　url←GETでデータを送信するためvalueを付ける
		//第二引数：非同期通信成功時の処理　data←コントローラからリターンされたデータを受け取る
		$.get("pulldown/child/" + value, function(data){
			//①中カテゴリのリスト
			//jQuery.parseJSON() 引数に設定された文字列をJSON（javaScriptオブジェクト）に変換
			var obj = $.parseJSON(data);
			//innerHTMLで毎回要素を空にして<select>内を空にする（どんどんappendされるのをふせぐため）
			$("#childCategory").html("");
			//子プルダウンの<select>内にfor文でappend
			$("#childCategory").append("<option value=''>-- child-category --</option>");
			for (var i = 0; i < obj.length; i++) {
				$("#childCategory").append("<option value=" + obj[i].itemValue + ">" + obj[i].itemLabel + "</option>");
			}
		})
	})

/**
 * 小プルダウン連動のためのjquery
 */
	$("#childCategory").change(function(){
		//プルダウンで選択された値を取得
		var value1 = $("#parentCategory option:selected").val();
		var value2 = $("#childCategory option:selected").val();
		//jQuery.get(url, data)　
		//第一引数：コントローラに送るためのパス　url←GETでデータを送信するためvalueを付ける
		//第二引数：非同期通信成功時の処理　data←コントローラからリターンされたデータを受け取る
		$.get("pulldown/grandChild/" + value1 + "/"+ value2, function(data){
			//jQuery.parseJSON() 引数に設定された文字列をJSON（javaScriptオブジェクト）に変換
			var obj = $.parseJSON(data);
			//innerHTMLで毎回要素を空にして<select>内を空にする（どんどんappendされるのをふせぐため）
			$("#grandchildCategory").html("");
			$("#grandchildCategory").append("<option value=''>-- grandchild-category --</option>");
			//子プルダウンの<select>内にfor文でappend
			for (var i = 0; i < obj.length; i++) {
				$("#grandchildCategory").append("<option value=" + obj[i].itemValue + ">" + obj[i].itemLabel + "</option>");
			}
		})
	})
});