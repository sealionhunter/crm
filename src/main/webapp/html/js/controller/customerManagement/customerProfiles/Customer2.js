Ext.define('CRM.controller.customerManagement.customerProfiles.Customer2', {
    extend: 'Ext.app.Controller',
    views: [ 'customerManagement.customerProfiles.CustomerTabInfo', 
             'customerManagement.customerProfiles.CustomerTab' ],
    stores: [ 'customerManagement.customerProfiles.Customer' ],
    init: function() {
        this.control({
            'customertab': {
                tabchange: this.tabchange
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
        initController('contactManagement.contactProfiles.Contact').viewInit(211, Ext.getCmp('customercontacttab'), this.record);
    },
    tabchange: function(card, previous) {
        var id = previous.id;
        if (id === 'customercontacttab') {
            initController('contactManagement.contactProfiles.Contact').viewInit(211, previous, this.record);
        } else if (id === 'customerlianxitab') {
            initController('customerManagement.contactTrack.ContactController').viewInit(121, previous, this.record);
        } else if (id ==='customerhistorytab') {
            initController('customerManagement.contactTrack.ContactHistory').viewInit(122, previous, this.record);
        } else if (id ==='leaderadvicetab') {
            initController('customerManagement.customerProfiles.LeaderAdvice').viewInit(114, previous, this.record);
        }
//    },
//    onPanelRendered2: function(panel) {
//        initController('contactManagement.contactProfiles.Contact').viewInit(211, panel, this.record);
//    },
//    onPanelRendered4: function(panel) {
//        initController('customerManagement.contactTrack.ContactController').viewInit(121, panel, this.record);
//    },
//    onPanelRendered5: function(panel) {
//        initController('customerManagement.contactTrack.ContactHistory').viewInit(122, panel, this.record);
//    },
//    onPanelRendered6: function(panel) {
//        initController('customerManagement.customerProfiles.LeaderAdvice').viewInit(114, panel, this.record);
    }
});