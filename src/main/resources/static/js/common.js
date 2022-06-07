const MyAjax = function() {
    function doGet(url, doSuccess, doError) {
        $.ajax({
            type: "GET",
            url: url,
            success: (response) => {
                doSuccess && doSuccess(response);
            },
            error: (xhr, status, error) => {
                doError && doError(xhr, status, error);
            }
        });
    }

    function doPost(url, data, doSuccess, doError) {
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: (response) => {
                doSuccess && doSuccess(response);
            },
            error: (xhr, status, error) => {
                doError && doError(xhr, status, error);
            }
        });
    }

    function doPut(url, data, doSuccess, doError) {
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: (response) => {
                doSuccess && doSuccess(response);
            },
            error: (xhr, status, error) => {
                doError && doError(xhr, status, error);
            }
        });
    }

    function doDelete(url, data, doSuccess, doError) {
        $.ajax({
            type: "POST",
            url: url,
            data: data,
            success: (response) => {
                doSuccess && doSuccess(response);
            },
            error: (xhr, status, error) => {
                doError && doError(xhr, status, error);
            }
        });
    }

    return {
        get: doGet,
        post: doPost,
        put: doPut,
        delete: doDelete,
    }
}();
