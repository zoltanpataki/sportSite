cloudinary.setCloudName('dw3yw87ju');

widget = cloudinary.createUploadWidget({ upload_preset: 'ekqulden' },
    function(error, result) {
        console.log(result[0].url);
        let data = {
            "url": result[0].url,
            "id": $("#picture").val()
        };
        console.log(data);
        $.ajax({
            url: '/uploadFile',
            type: 'POST',
            contentType: 'application/json; charset=UTF-8',
            data: JSON.stringify(data),
            success: function (response) {
                location.reload(true);
            }
        })});

$('#uploadButton').on('click', function (event) {
    event.preventDefault();
    widget.open();
});