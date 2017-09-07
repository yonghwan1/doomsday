isset = function(a) {
    return "undefined" !== typeof localStorage[a]
};
var grt = {
    grt_name: null,
    grt_pen: null,
    list: null,
    save_message_flag: !1,
    flag: {
        grt_name_message: !1
    },
    updateName: function() {
        var a = !1,
            b = $("#grt_name").val();
        0 < b.length && b != grt.grt_name && (grt.grt_name = b, localStorage.grt_name = grt.grt_name, a = !0);
        b = $("#grt_pen").val();
        0 < b.length && b != grt.grt_pen && (grt.grt_pen = b, localStorage.grt_pen = grt.grt_pen, a = !0);
        a && !grt.flag.grt_name_message && ($("#grt_name_message").html("\u4fdd\u5b58\u3057\u307e\u3057\u305f\u3002"), grt.flag.grt_name_message = !0, $("#grt_name_message").fadeIn(),
            setTimeout(function() {
                $("#grt_name_message").fadeOut();
                grt.flag.grt_name_message = false
            }, 5E3))
    },
    checkName: function() {
        isset("grt_name") && 1 < localStorage.grt_name.length ? ($("#grt_name").val(localStorage.grt_name), grt.grt_name = localStorage.grt_name) : location.href.match(/edit.html/) ? location.href = "/editor/list.html" : alert("crowdworks\u30e6\u30fc\u30b6\u30fc\u540d\u3092\u5165\u529b\u3057\u3066\u4e0b\u3055\u3044\u3002");
        isset("grt_pen") && 1 < localStorage.grt_pen.length && ($("#grt_pen").val(localStorage.grt_pen),
            grt.grt_pen = localStorage.grt_pen)
    },
    delContent: function(a) {
        var b = grt.edit.genId(grt.edit.memoidBy(a));
        localStorage.removeItem(b);
        for (a = parseInt(a); a < grt.edit.tabs.count; a++) {
            var b = grt.edit.genId(grt.edit.memoidBy(a)),
                c = grt.edit.genId(grt.edit.memoidBy(a + 1)),
                d = {};
            if (isset(c) && "undefined" !== localStorage[c]) d = JSON.parse(localStorage[c]);
            else var d = (new Date).getTime(),
                e = (new Date).getTime(),
                d = {
                    eid: "",
                    body: "",
                    created: d,
                    updated: e
                };
            d.eid = grt.edit.memoidBy(a);
            localStorage[b] = JSON.stringify(d);
            a + 1 == grt.edit.tabs.count &&
                localStorage.removeItem(c)
        }
    },
    saveContent: function(a) {
        var b = grt.edit.memoid(),
            c = grt.getContent(b),
            d = $("#memo").val();
        grt.edit.setLinks(d);
        var e = (new Date).getTime(),
            f = (new Date).getTime();
        if (a || !c || c.body != d) return c && (e = c.created), a = {
            eid: b,
            body: d,
            created: e,
            updated: f
        }, localStorage["grt_con_" + b] = JSON.stringify(a), $("#save-message").is(":visible") || ($("#save-message").html("\u4fdd\u5b58\u3057\u307e\u3057\u305f\u3002" + b), $("#save-message").fadeIn(), setTimeout(function() {
                $("#save-message").fadeOut()
            },
            5E3)), a
    },
    getContent: function(a) {
        a = "grt_con_" + a;
        if (isset(a)) try {
            return JSON.parse(localStorage[a])
        } catch (b) {}
        return !1
    },
    delList: function(a) {
        var b = grt.getList(),
            c = [],
            d;
        for (d in b) b[d].eid != a && c.push(b[d]);
        localStorage.grt_list = JSON.stringify(c);
        grt.checkList()
    },
    saveList: function(a) {
        var b = grt.getList(),
            c = {
                eid: a.eid,
                title: a.title,
                created: a.created,
                updated: a.updated,
                stat: a.stat
            },
            d = !1,
            e;
        for (e in b)
            if (b[e].eid == a.eid) {
                b[e] = c;
                d = !0;
                break
            }
        d || b.unshift(c);
        localStorage.grt_list = JSON.stringify(b)
    },
    getList: function() {
        var a = [];
        isset("grt_list") && (a = JSON.parse(localStorage.grt_list));
        return a
    },
    checkList: function() {
        var a = grt.getList();
        if (0 < a.length) {
            var b = '<table class="table"><tr><th>\u30bf\u30a4\u30c8\u30eb</th><th>\u4f5c\u6210\u65e5\u6642</th><th>\u66f4\u65b0\u65e5\u6642</th><th>\u72b6\u614b</th><th>\u524a\u9664</th></tr>',
                c = new Date;
            $.each(a, function(a, e) {
                b += '<tr><td><a href="/editor/edit.html#id=' + e.eid + '">' + e.title + "</a></td>";
                c.setTime(e.created);
                b += "<td>" + (c.getMonth() + 1) + "/" + c.getDate() + " " + c.getHours() + ":" + c.getMinutes() +
                    ":" + c.getSeconds() + "</td>";
                c.setTime(e.updated);
                b += "<td>" + (c.getMonth() + 1) + "/" + c.getDate() + " " + c.getHours() + ":" + c.getMinutes() + ":" + c.getSeconds() + "</td>";
                var f = "undefined" !== typeof e.stat ? e.stat : 0;
                b += "<td>";
                switch (f) {
                    case 1:
                        b += "\u6295\u7a3f\u6e08\u307f";
                        break;
                    default:
                        b += "\u4e0b\u66f8\u304d"
                }
                b += "</td><td>";
                0 == f && (b += '<button class="btn btn-danger" onclick="grt.delContent(\'' + e.eid + "');\">\u524a\u9664</a>");
                b += "</td>";
                b += "</tr>"
            });
            $("#grt_list").html(b)
        } else $("#grt_list").html("\u307e\u3060\u8a18\u4e8b\u306f\u3042\u308a\u307e\u305b\u3093\u3002")
    },
    edit: {
        tab: null,
        genId: function(a) {
            return "grt_con_" + a
        },
        memoid: function() {
            return "memo-" + grt.edit.tabs.now
        },
        memoidBy: function(a) {
            return "memo-" + a
        },
        tabs: {
            TAB_NOW: "grt_tab_now",
            TAB_COUNT: "grt_tab_count",
            now: 1,
            count: 3,
            MIN: 3,
            select: function() {
                var a = $(this).attr("data-num");
                parseInt(a) <= grt.edit.tabs.count && (grt.edit.tabs.now = a, grt.edit.tabs.load(), grt.edit.tabs.save(), grt.edit.tabs.setTab())
            },
            load: function() {
                var a = grt.getContent(grt.edit.memoid());
                a ? $("#memo").val(a.body) : $("#memo").val("");
                grt.edit.setLinks()
            },
            save: function() {
                localStorage[grt.edit.tabs.TAB_NOW] = grt.edit.tabs.now;
                localStorage[grt.edit.tabs.TAB_COUNT] = grt.edit.tabs.count
            },
            deleteTab: function() {
                grt.edit.hideDeleteMessage();
                var a = grt.edit.tabs.now;
                grt.edit.tabs.count > grt.edit.tabs.MIN && (grt.delContent(a), grt.edit.tabs.count--, grt.edit.tabs.now = a - 1, grt.edit.tabs.removeLastTab(), grt.edit.tabs.save(), grt.edit.tabs.load(), grt.edit.tabs.setTab())
            },
            addTab: function() {
                grt.edit.tabs.count++;
                grt.edit.tabs.now = grt.edit.tabs.count;
                grt.edit.tabs.save();
                grt.edit.tabs.load();
                grt.edit.tabs.setTab()
            },
            removeLastTab: function() {
                $(".tabmenu").each(function() {
                    $(this).attr("data-num") >= grt.edit.tabs.count + 1 && $(this).remove()
                })
            },
            selecting: !1,
            setTab: function() {
                grt.edit.tabs.count <= grt.edit.tabs.MIN ? $("#tab-delete-check").hide() : $("#tab-delete-check").show();
                if ($(".tabmenu").length < grt.edit.tabs.count) {
                    var a = $(".tabmenu").length + 1;
                    for (a; a <= grt.edit.tabs.count; a++) $("#tabmenus").append('<a class="tabmenu btn btn-default" style="width:42px;" data-num="' + a + '">' +
                        a + "</a>&nbsp;"), 0 == a % 10 && $("#tabmenus").append("<br>")
                }
                $(".tabmenu").each(function() {
                    var a = $(this),
                        c = a.attr("data-num");
                    a.unbind("click", grt.edit.tabs.select);
                    c != grt.edit.tabs.now ? (a.removeClass("btn-warning"), a.hasClass("btn-default") || a.addClass("btn-default"), a.bind("click", grt.edit.tabs.select)) : (a.removeClass("btn-default"), a.hasClass("btn-warning") || a.addClass("btn-warning"))
                });
                $("#tab-add").unbind("click", grt.edit.tabs.addTab);
                $("#tab-add").bind("click", grt.edit.tabs.addTab)
            }
        },
        init: function() {
            isset(grt.edit.tabs.TAB_NOW) &&
                (grt.edit.tabs.now = localStorage[grt.edit.tabs.TAB_NOW]);
            isset(grt.edit.tabs.TAB_COUNT) && (grt.edit.tabs.count = localStorage[grt.edit.tabs.TAB_COUNT]);
            grt.edit.tabs.load();
            $("#to_gmail").click(grt.send.gmail);
            $("#to_twitter").click(grt.send.twitter);
            $("#add_time").click(grt.edit.addTime);
            $("#add_url").click(grt.edit.addUrl);
            $("#tab-delete-check").click(grt.edit.showDeleteMessage);
            grt.edit.tabs.setTab()
        },
        showDeleteMessage: function() {
            $("#delete-message").show();
            $("#tab-delete").unbind("click", grt.edit.tabs.deleteTab);
            $("#tab-delete").click(grt.edit.tabs.deleteTab);
            $("#tab-delete-cancel").unbind("click", grt.edit.hideDeleteMessage);
            $("#tab-delete-cancel").click(grt.edit.hideDeleteMessage)
        },
        hideDeleteMessage: function() {
            $("#tab-delete").unbind("click", grt.edit.tabs.deleteTab);
            $("#tab-delete-cancel").unbind("click", grt.edit.hideDeleteMessage);
            $("#delete-message").hide()
        },
        generateId: function(a) {
            var b = "";
            for (i in [0, 1, 2, 3, 4, 5]) b += "abcdefgha".substr(parseInt(8 * Math.random()), 1);
            var c = 10 > a.getMonth() + 1 ? "0" + (a.getMonth() +
                    1) : a.getMonth() + 1,
                d = 10 > a.getDate() ? "0" + a.getDate() : a.getDate(),
                e = 10 > a.getHours() ? "0" + a.getHours() : a.getHours(),
                f = 10 > a.getMinutes() ? "0" + a.getMinutes() : a.getMinutes(),
                g = 10 > a.getSeconds() ? "0" + a.getSeconds() : a.getSeconds(),
                h = a.getMilliseconds();
            return b + "-" + a.getFullYear() + c + d + e + f + g + "-" + h
        },
        checkHash: function() {
            var a = new Date,
                b = location.hash,
                c = null;
            (m = b.match(/id=([a-z0-9\-\_]+)/)) ? (c = m[1], a = grt.getContent(c), grt.edit.setContent(a)) : (b.match(/new/), c = grt.edit.generateId(a), $("#text_title").val("\u672a\u8a2d\u5b9a " +
                c), location.hash = "id=" + c);
            $("#grt_eid").val(c)
        },
        setContent: function(a) {
            $("#text_title").val(a.title);
            $("#text_body").val(a.body);
            $("#text_amazon").val(a.amazon);
            $("#text_memo").val(a.memo);
            grt.edit.check_text()
        },
        save_tmp: function() {
            var a = $("#text_body").val(),
                a = {
                    time: (new Date).getTime(),
                    data: a
                },
                b = [];
            "undefined" !== typeof localStorage.save_tmp_open && (b = JSON.parse(localStorage.save_tmp_open));
            b.unshift(a);
            3 < b.length && b.pop();
            localStrage.setItem("save_tmp_open", JSON.stringify(b))
        },
        check_text: function() {
            var a =
                $("#memo").html().split(/[\r\n]+/);
            $("#line_count").html(a.length);
            var b = 0,
                c = 0,
                d = 0,
                e;
            for (e in a) {
                var f = a[e].replace(/[\r\n]/, ""),
                    b = b + f.length;
                f.match(/^>/) ? d += f.length : c += f.length
            }
            $("#all_cnt").html(b);
            $("#main_cnt").html(c);
            $("#sub_cnt").html(d)
        },
        getAreaRange: function(a) {
            var b = {};
            window.getSelection() && (b.start = a.selectionStart, b.end = a.selectionEnd);
            return b
        },
        delBlock: function() {
            var a = document.getElementById("text_body"),
                b = grt.edit.getAreaRange(a),
                c = a.value,
                d = c.slice(b.start, b.end),
                e = c.slice(0, b.start),
                c = c.slice(b.end),
                f = "";
            if (d || b.start != b.end) {
                var b = d.split(/[\r\n]+/),
                    g;
                for (g in b) f += b[g].replace(/^> /, "") + "\r\n";
                a.value = e + f + c;
                grt.edit.check_text()
            }
        },
        setBlock: function() {
            var a = document.getElementById("text_body"),
                b = grt.edit.getAreaRange(a),
                c = a.value,
                d = c.slice(b.start, b.end),
                e = c.slice(0, b.start),
                c = c.slice(b.end),
                f = "";
            if (d || b.start != b.end) {
                var b = d.split(/[\r\n]+/),
                    g;
                for (g in b) f += "> " + b[g] + "\r\n";
                a.value = e + f + c;
                grt.edit.check_text()
            }
        },
        surroundHTML: function(a, b, c) {
            var c = document.getElementById(c),
                d = grt.edit.getAreaRange(c),
                e = c.value,
                f = e.slice(d.start, d.end),
                g = e.slice(0, d.start),
                e = e.slice(d.end);
            f || d.start != d.end ? c.value = g + (a + f + b) + e : d.start == d.end && (c.value = g + (a + b) + e)
        },
        links_html: "",
        setLinks: function(a) {
            "undefined" === typeof a && (a = (a = grt.getContent(grt.edit.memoid())) ? a.body : "");
            a = a.match(/(https?:\/\/[^\/]+)([^ \r\n]*)/g);
            grt.edit.links_html = '<span class="label label-default">links</span> &nbsp; ';
            if (a && 0 < a.length) {
                var b = 0;
                $.each(a, function(a, d) {
                    b++;
                    grt.edit.links_html = grt.edit.links_html +
                        ('<a href="' + d + '" title="' + d + '" target="_blank"><span class="glyphicon glyphicon-tag"></span></a> ')
                });
                0 < a.length && $("#links").html(grt.edit.links_html)
            } else $("#links").html(grt.edit.links_html)
        },
        getTab: function() {
            chrome.tabs.getCurrent(function(a) {
                grt.edit.tab = a
            })
        },
        addUrl: function() {
            chrome.tabs.query({
                active: !0,
                currentWindow: !0
            }, function(a) {
                a && "undefined" !== typeof a && "undefined" !== typeof a[0] && (grt.edit.surroundHTML(a[0].title + "\n" + a[0].url + "\n", "", "memo"), grt.saveContent())
            })
        },
        addTime: function() {
            var a =
                new Date,
                b = 10 > a.getMonth() + 1 ? "0" + (a.getMonth() + 1) : a.getMonth() + 1,
                c = 10 > a.getDate() ? "0" + a.getDate() : a.getDate(),
                d = 10 > a.getHours() ? "0" + a.getHours() : a.getHours(),
                e = 10 > a.getMinutes() ? "0" + a.getMinutes() : a.getMinutes(),
                a = 10 > a.getSeconds() ? "0" + a.getSeconds() : a.getSeconds();
            grt.edit.surroundHTML(b + "/" + c + " " + d + ":" + e + ":" + a, "\n", "memo");
            grt.saveContent()
        }
    },
    file: {
        save_as: function() {
            chrome.fileSystem.chooseEntry({
                type: "saveFile"
            }, function(a) {
                var b = grt.getContent("tmp");
                a.createWriter(function(a) {
                    a.onerror = errorHandler;
                    a.onwriteend = function() {};
                    a.write(new Blob([b.body], {
                        type: "text/plain"
                    }))
                }, errorHandler)
            })
        }
    },
    send: {
        gmail: function() {
            var a = $("#memo").val();
            $("#mail_title").val("memo chrome");
            $("#mail_body").val(a);
            $("#mail_send").submit()
        },
        twitter: function() {
            var a = $("#memo").val();
            window.open("http://twitter.com/home?status=" + encodeURIComponent(a), "_blank")
        }
    }
};
$(document).ready(function() {
    grt.edit.init();
    $(window).keyup(function() {
        grt.saveContent()
    })
});