Ext.Loader.setConfig({
    enabled: true
});

Ext.require([ 'Ext.tree.*', 'Ext.window.*', 'Ext.grid.*' ]);

Ext.onReady(function() {
    Ext.QuickTips.init();
    // Deleted for EXTJS 4.2 start 20150302
    // Ext.form.Field.prototype.msgTarget = 'side';
    // Deleted for EXTJS 4.2 start 20150302
    Ext.application({
        name: 'CRM',
        appFolder: 'html/js',
        launch: function() {
            var thisApp = this;
            initController = function(controlName) {
                var c_init = thisApp.controllers.get(controlName);
                if (!c_init) {
                    c_init = thisApp.getController(controlName);
                    // Delete for EXTJS 4.2 start 20150302
                    // c_init.init();
                    // Delete for EXTJS 4.2 end 20150302
                }
                return c_init;
            };
            Ext.create('CRM.view.main.Main');
        },
        controllers: [ 'menu.Menu' ]
    });
});
