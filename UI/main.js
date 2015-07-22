var firstNumber;
var lastNumber;
var lastOperation;

function numbers(number) {

    if ($("#result").text() == "0")
        $("#result").text(number); 
    else
        $("#result").append(number);

    lastOperation = null;

    try {
        $(".active").removeClass('active');
    } catch (nfe) {
        console.log('try'+nfe);
    }
    /*
    if (($("#result").text() == "0") || ($('#result').text() == firstNumber)) {
        $("#result").text(number);
    }
    else {
        $("#result").append(number);
    }
    console.log('First number (numbers): '+firstNumber);
    console.log('Last number (numbers): '+lastNumber);
    console.log('Last operation (numbers): ' + lastOperation);
    */
}

function tools(tool) {
    if (tool == "c")
        $("#result").text("0");
}

function operations(operation) {
    
    if ($("#result").text() == "0") {
        
        if (operation == "left_bracket") {
            $("#result").text("");
        } else {
            return
        }
    }

    try {
        $(".active").removeClass('active');
    } catch (nfe) {
        console.log('try'+nfe);
    }
    
    if (operation == 'plus') {
        $("#plus").addClass("active");

        if (lastOperation != 'plus')
            $("#result").append(" + ");

        lastOperation = 'plus';
    }
    
    if (operation == 'minus') {
        $("#minus").addClass("active");

        if (lastOperation != 'minus')
            $("#result").append(" - ");
        lastOperation = 'minus';
    }
    
    if (operation == 'times') {
        $("#times").addClass("active");

        if (lastOperation != 'times')
            $("#result").append(" * ");
        lastOperation = 'times';
    }
    
    if (operation == 'under') {
        $("#under").addClass("active");

        if (lastOperation != 'under')
            $("#result").append(" / ");

        lastOperation = 'under';
    }

    if (operation == 'left_bracket') {
        $("#left_bracket").addClass("active");
        
        if (lastOperation != 'left_bracket')
            $("#result").append(" ( ");

        lastOperation = 'left_bracket';
    }

    if (operation == 'right_bracket') {
        $("#right_bracket").addClass("active");
        
        if (lastOperation != 'right_bracket')
            $("#result").append(" ) ");

        lastOperation = 'right_bracket';
    }
    
    if (operation == 'equal') {
        console.log($("#result").text().replace(/ /g,'').replace("=",''));
        $.ajax({
            url: "http://localhost:9000",
            type:"POST",  
            contentType: "application/x-www-form-urlencoded; charset=UTF-8",  
            data:$("#result").text().replace(/ /g,'').replace("=",'')
            //beforeSend:showRequest,  

            }).done(function(data) {
                console.log("result : ",data);
                $("#result").text(data);
            }).fail(function() {
                alert("Request Fail")
            });
        lastOperation = null;
    }
}