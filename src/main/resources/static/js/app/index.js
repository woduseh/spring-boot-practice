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

    $('#btn-check-math').on('click', function () {
      _this.checkMath();
    });
  },

  save: function () {
    let data = {
      title: $('#title').val(),
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
  },

  checkMath: function () {
    let mathExpression = $('#mathExpression').val()

    $.ajax({
      type: 'POST',
      url: '/api/maths/save',
      dataType: 'json',
      contentType: 'application/json; charset=utf-8',
      data: JSON.stringify({
        expression: mathExpression
      })
    }).done(function () {
      alert('수식이 등록되었습니다.');
      window.location.href = '/maths';
    }).fail(function (error) {
      alert(JSON.stringify(error));
    });
  },
};

main.init();