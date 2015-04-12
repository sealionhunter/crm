Ext.define('CRM.controller.customerManagement.customerProfiles.Customer2', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.customerProfiles.CustomerTabInfo', 
             'customerManagement.customerProfiles.CustomerTab' ],
    stores: [ 'customerManagement.customerProfiles.Customer' ],
    init: function() {
        this.control({
            'customertab panel[id=customercontacttab]': {
                render: this.onPanelRendered2
            },
            'customertab panel[id=customerlianxitab]': {
                render: this.onPanelRendered4
            },
            'customertab panel[id=customerhistorytab]': {
                render: this.onPanelRendered5
            },
            'customertab panel[id=leaderadvicetab]': {
                render: this.onPanelRendered6
            }
        });
    },
    viewInit: function(record) {
        var customertab = Ext.getCmp('customertabinfo');
        if (typeof (customertab) !== 'undefined') {
            customertab.destroy();
        }
        this.record = record;
        customertab = Ext.widget('customertabinfo');
        customertab.down("form").loadRecord(record);
        Ext.getCmp('centercard').insert(1, customertab);
        Ext.getCmp('centercard').getLayout().setActiveItem('customertabinfo');
    },
    onPanelRendered2: function(panel) {
        initController('contactManagement.contactProfiles.Contact').viewInit(211, panel, this.record);
    },
    onPanelRendered4: function(panel) {
        initController('customerManagement.contactTrack.ContactController').viewInit(121, panel, this.record);
    },
    onPanelRendered5: function(panel) {
        initController('customerManagement.contactTrack.ContactHistory').viewInit(122, panel, this.record);
    },
    onPanelRendered6: function(panel) {
        initController('customerManagement.customerProfiles.LeaderAdvice').viewInit(114, panel, this.record);
    }
});