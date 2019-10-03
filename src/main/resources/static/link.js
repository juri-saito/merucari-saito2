/**
 * 各商品のブランド・カテゴリクリック時に検索する
 */
    // ブランド名リンククリック時のイベント処理設定
    $('.brandLink').on('click', function() {
      $('#forms [name=brand]').val($(this).text());
      $('#forms [name=page]').val(1);
      $('#forms').submit();
    });