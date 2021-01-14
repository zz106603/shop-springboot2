var main = {
    init : function () {
        var _this = this;
        $('#btn-save-member').on('click', function () {
            _this.save();
        });

        $('#btn-update-member').on('click', function () {
            _this.update();
        });

        $('#btn-delete-member').on('click', function () {
            _this.delete();
        });

        $('.id_button').on('click', function () {
            _this.id_button();
        });
    },
    save : function () {
        var data = {
            memberId: $('#memberId').val(),
            memberPw: $('#memberPw').val(),
            memberName: $('#memberName').val(),
            memberEmail: $('#memberEmail').val(),
            memberAddr1: $('#memberAddr1').val(),
            memberAddr2: $('#memberAddr2').val(),
            memberAddr3: $('#memberAddr3').val(),
        };

        $.ajax({
            type: 'POST',
            url: '/api/v1/member',
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            if(confirm('회원가입을 하시겠습니까?')) {
                alert('정상적으로 회원가입 되었습니다.');
                window.location.href = '/notice/main.do';
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },
    update : function () {
        var data = {
            memberId: $('#memberId').val(),
            memberPw: $('#memberPw').val(),
            memberName: $('#memberName').val(),
            memberEmail: $('#memberEmail').val(),
            memberAddr1: $('#memberAddr1').val(),
            memberAddr2: $('#memberAddr2').val(),
            memberAddr3: $('#memberAddr3').val(),
        };

        var idx = $('#idx').val();

        $.ajax({
            type: 'PUT',
            url: '/api/v1/member/'+idx,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function() {
            if(confirm('회원정보를 수정하시겠습니까?')) {
                alert('회원 정보가 수정되었습니다.');
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
            url: '/api/v1/member/'+idx,
            dataType: 'json',
            contentType:'application/json; charset=utf-8'
        }).done(function() {
            if(confirm('탈퇴 하시겠습니까?')){
                alert('탈퇴가 완료되었습니다.');
                window.location.href = '/notice/main.do';
            }
        }).fail(function (error) {
            alert(JSON.stringify(error));
        });
    },

    id_button : function () {
        var memberId = $('#memberId').val();
        var data = {memberId : memberId}

        $.ajax({
            type: 'POST',
            url: '/api/v1/member/IdCheck',
            data : data,
            success : function (result){
                if(result != 'fail'){
                    $('.id_input_re_1').css("display", "inline-block");
                    $('.id_input_re_2').css("display", "none");
                }else{
                    $('.id_input_re_1').css("display", "none");
                    $('.id_input_re_2').css("display", "inline-block");
                }
            }
        });
    }

};

main.init();