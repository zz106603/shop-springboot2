var main = {
    init : function () {
        var _this = this;
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
    save : function () {
        var data = {
            title: $('#title').val(),
            writer: $('#writer').val(),
            content: $('#content').val()
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/notice',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            if(confirm('글을 등록하시겠습니까?')) {
                alert('글이 등록되었습니다.');
                window.location.href = '/notice/main.do';
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            title: $('#title').val(),
            writer: $('#writer').val(),
            content: $('#content').val()
        };

        var idx = $('#idx').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/notice/'+idx,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            if(confirm(idx+'번 글을 수정하시겠습니까?')) {
                alert('글이 수정되었습니다.');
                window.location.href = '/notice/main.do';
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    delete : function () {
        var idx = $('#idx').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/notice/'+idx,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            if(confirm(idx+'번 글을 삭제하시겠습니까?')){
                alert('글이 삭제되었습니다.');
                window.location.href = '/notice/main.do';
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    }

};

main.init();