$(document).ready(function () {
    const dom = {
        init: function () {
            console.log("loaded");
            eventApplier.addEventToNews();
        }
    };
    
    const events = {
        pressNews : function (event) {
            var index = this.getAttribute("id").substring(11);
            if (document.getElementById("newsdescriptionid" + index).getAttribute("hidden") === null) {
                document.getElementById("newsdescriptionid" + index).setAttribute("hidden", "hidden");
            } else {
                document.getElementById("newsdescriptionid" + index).removeAttribute("hidden");
            }
        }
    };
    
    const eventApplier = {
        addEventToNews: function () {
            $(".newstitle").click(events.pressNews);
        }
    };

    dom.init();
});