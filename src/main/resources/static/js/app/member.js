var idCheck = false;
var idckCheck = false;
var pwCheck = false;
var pwckCheck = false;
var nameCheck = false;
var emailCheck = false;
var emailckCheck = false;
var addressCheck = false;

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

        $('#btn-login-member').on('click', function () {
            _this.login();
        });

        /* 아이디 중복 확인 */
        $('.id_button').on('click', function () {
            _this.id_button();
        });

        /* 이메일 중복 확인 */
        $('.email_button').on('click', function () {
            _this.email_button();
        });

        /* 비밀번호 확인 */
        $('.pwck_input').on('propertychange change keyup paste input', function () {
            _this.pwck_input();
        });

        /* 빈 칸이 있어서 회원가입이 이루어지지 않을 때 ,그 칸을 다시 누르면 메시지 삭제 */
        $('.id_input').on('click', function () {
            $('.final_id_ck').css('display', 'none');
        });
        $('.pw_input').on('click', function () {
            $('.final_pw_ck').css('display', 'none');
        });
        $('.pwck_input').on('click', function () {
            $('.final_pwck_ck').css('display', 'none');
        });
        $('.user_input').on('click', function () {
            $('.final_name_ck').css('display', 'none');
        });
        $('.mail_input').on('click', function () {
            $('.final_email_ck').css('display', 'none');
        });
        $('.address_input_3').on('click', function () {
            $('.final_addr_ck').css('display', 'none');
        });
    },
    save : function () {
        var data = {
            memberId: $('#memberId').val(),
            memberPw: $('#memberPw').val(),
            memberPwCk : $('#memberPwCk').val(),
            memberName: $('#memberName').val(),
            memberEmail: $('#memberEmail').val(),
            memberAddr1: $('#memberAddr1').val(),
            memberAddr2: $('#memberAddr2').val(),
            memberAddr3: $('#memberAddr3').val(),
        };

        /* 아이디 유효성 검사 */
        if(data.memberId == ""){
            $('.final_id_ck').css('display', 'block');
            idCheck = false;
        }else{
            $('.final_id_ck').css('display', 'none');
            idCheck = true;
        }

        /* 비밀번호 유효성 검사 */
            var pw = data.memberPw;
            /* 비밀번호 정규식  */
            function pwFormCheck(pw) {
                var form = /^(?=.*[A-Za-z])(?=.*\d)(?=.*[$@$!%*#?&])[A-Za-z\d$@$!%*#?&]{8,}$/;
                return form.test(pw);
            }

            if(data.memberPw == ""){
                $('.final_pw_ck').css('display', 'block');
                $('.final_repw_ck').css('display', 'none');
                $('.pwck_input_re_1').css('display', 'none');
                pwCheck = false;
            }else if(!pwFormCheck(pw)) {
                $('.final_pw_ck').css('display', 'none');
                $('.final_repw_ck').css('display', 'block');
                $('.pwck_input_re_1').css('display', 'none');
                pwCheck = false;
            } else {
                $('.final_pw_ck').css('display', 'none');
                $('.final_repw_ck').css('display', 'none');
                pwCheck = true;
            }

        /* 비밀번호 확인 유효성 검사 */
        if(data.memberPwCk == ""){
            $('.final_pwck_ck').css('display', 'block');
            pwckCheck = false;
        }else{
            $('.final_pwck_ck').css('display', 'none');
            pwckCheck = true;
        }

        /* 이름 유효성 검사 */
        if(data.memberName == ""){
            $('.final_name_ck').css('display', 'block')
            nameCheck = false;
        }else{
            $('.final_name_ck').css('display', 'none')
            nameCheck = true;
        }

        /* 이메일 유효성 검사 */
        if(data.memberEmail == ""){
            $('.final_email_ck').css('display', 'block')
            emailCheck = false;
        }else{
            $('.final_email_ck').css('display', 'none')
            emailCheck = true;
        }

        /* 주소 유효성 검사 */
        if(data.memberAddr3 == ""){
            $('.final_addr_ck').css('display', 'block')
            addressCheck = false;
        }else{
            $('.final_addr_ck').css('display', 'none')
            addressCheck = true;
        }

        if(idCheck&&idckCheck&&pwCheck&&pwckCheck&&nameCheck&&emailCheck&&addressCheck&&emailckCheck){
            $.ajax({
                type: 'POST',
                url: '/api/v1/member/join',
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
        }else{
            return false;
        }
    },

    id_button : function () {
        var memberId = $('#memberId').val();
        var warnMsg = $('.id_input_box_warn');
        var data = {memberId : memberId}

        /* 아이디 정규식  */
        function idFormCheck(id){
            var form = /^[a-zA-Z]{1}[a-zA-Z0-9_]{4,11}$/;
            return form.test(id);
        }
        if(!idFormCheck(memberId)){
            warnMsg.html("올바르지 못한 아이디 형식입니다.");
            warnMsg.css('display', 'inline-block');
            warnMsg.css('color', 'red');
            $('.id_input_re_1').css("display", "none");
            $('.id_input_re_2').css("display", "none");
            return false;
        }else{
            warnMsg.html("");
        }


        $.ajax({
            type: 'POST',
            url: '/api/v1/member/join/IdCheck',
            data : data,
            success : function (result){
                if(result != 'fail'){
                    $('.id_input_re_1').css("display", "inline-block");
                    $('.id_input_re_2').css("display", "none");
                    idckCheck = true;
                }else{
                    $('.id_input_re_1').css("display", "none");
                    $('.id_input_re_2').css("display", "inline-block");
                    idckCheck = false;
                }
            }
        });
    },

    email_button : function () {

        var memberEmail = $('#memberEmail').val();
        var warnMsg = $('.mail_input_box_warn');
        var data = {memberEmail : memberEmail}

        /* 이메일 정규식 */
        function mailFormCheck(email){
            var form = /^([\w-]+(?:\.[\w-]+)*)@((?:[\w-]+\.)*\w[\w-]{0,66})\.([a-z]{2,6}(?:\.[a-z]{2})?)$/i;
            return form.test(email);
        }
        if(!mailFormCheck(memberEmail)){
            warnMsg.html("올바르지 못한 이메일 형식입니다.");
            warnMsg.css('display', 'inline-block');
            warnMsg.css('color', 'red');
            $('.mail_input_re_1').css("display", "none");
            $('.mail_input_re_2').css("display", "none");
            return false;
        }else{
            warnMsg.html("");
        }

        $.ajax({
            type: 'POST',
            url: '/api/v1/member/join/EmailCheck',
            data : data,
            success : function (result){
                if(result != 'fail'){
                    $('.mail_input_re_1').css("display", "inline-block");
                    $('.mail_input_re_2').css("display", "none");
                    emailckCheck = true;
                }else{
                    $('.mail_input_re_1').css("display", "none");
                    $('.mail_input_re_2').css("display", "inline-block");
                    emailckCheck = false;
                }
            }
        });
    },

    pwck_input : function () {
        var memberPw = $('#memberPw').val();
        var memberPwCk = $('#memberPwCk').val();
        $('.final_pwck_ck').css('display', 'none');

        if(memberPw == memberPwCk){
            $('.pwck_input_re_1').css('display', 'block');
            $('.pwck_input_re_2').css('display', 'none');
            pwckCheck = true;
        }else{
            $('.pwck_input_re_1').css('display', 'none');
            $('.pwck_input_re_2').css('display', 'block');
            pwckCheck = false;
        }
    },
    /*
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
            url: '/api/v1/member/join/'+idx,
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
            url: '/api/v1/member/join/'+idx,
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
    }
    */

    login : function () {

        $("#login_form").attr("action", "/member/login.do");
        $("#login_form").submit();

    }

};

main.init();
