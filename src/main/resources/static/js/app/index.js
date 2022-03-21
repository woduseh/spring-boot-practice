/**
 * <pre>
 * author           : JYHwang
 * date             : 2022-03-21
 * description      :
 * </pre>
 * ===========================================================
 * <pre>
 * DATE                 AUTHOR                  NOTE
 * -----------------------------------------------------
 * 2022-03-21           JYHwang                 최초 생성
 * </pre>
 */

let main = {
  init: function () {
    let _this = this;
    $('#btn-save').on('click', function () {
      _this.save();
    });

    $('#btn-update').on('click', function () {
      _this.update();
    });

    $('#btn-delete').on('click', function () {
      _this.delete();
    });
  },
  save: function () {
    let data = {
      title: $('#title').val(),
      author: $('#author').val(),
      content: $('#content').val()
    };

    $.ajax({
      type: 'POST',
      url: '/api/posts',
      dataType: 'json',
      contentType: 'application/json; charset=utf-8',
      data: JSON.stringify(data)
    }).done(function () {
      alert('글이 등록되었습니다.');
      window.location.href = '/';
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },
  update: function () {
    let data = {
      title: $('#title').val(),
      content: $('#content').val()
    };

    let id = $('#id').val();

    $.ajax({
      type: 'PATCH',
      url: '/api/posts/' + id,
      dataType: 'json',
      contentType: 'application/json; charset=utf-8',
      data: JSON.stringify(data)
    }).done(function () {
      alert('글이 수정되었습니다.');
      window.location.href = '/';
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },
  delete: function () {
    let id = $('#id').val();

    $.ajax({
      type: 'DELETE',
      url: '/api/posts/' + id,
      dataType: 'json',
      contentType: 'application/json; charset=utf-8'
    }).done(function () {
      alert('글이 삭제되었습니다.');
      window.location.href = '/';
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  }

};

main.init();