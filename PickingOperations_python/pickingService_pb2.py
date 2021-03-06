# -*- coding: utf-8 -*-
# Generated by the protocol buffer compiler.  DO NOT EDIT!
# source: pickingService.proto
"""Generated protocol buffer code."""
from google.protobuf import descriptor as _descriptor
from google.protobuf import descriptor_pool as _descriptor_pool
from google.protobuf import message as _message
from google.protobuf import reflection as _reflection
from google.protobuf import symbol_database as _symbol_database
# @@protoc_insertion_point(imports)

_sym_db = _symbol_database.Default()




DESCRIPTOR = _descriptor_pool.Default().AddSerializedFile(b'\n\x14pickingService.proto\x12\x11pickingOperations\".\n\nBinRequest\x12\x0b\n\x03sku\x18\x01 \x01(\t\x12\x13\n\x0bproductName\x18\x02 \x01(\t\"\x1a\n\x0b\x42inResponse\x12\x0b\n\x03\x62in\x18\x01 \x01(\t\"A\n\x0bPickRequest\x12\x0b\n\x03\x62in\x18\x01 \x01(\t\x12\x13\n\x0bproductName\x18\x02 \x01(\t\x12\x10\n\x08quantity\x18\x03 \x01(\x05\"5\n\x0cPickResponse\x12\x15\n\rresultMessage\x18\x01 \x01(\t\x12\x0e\n\x06pickId\x18\x02 \x01(\t2\xa6\x01\n\x07Picking\x12I\n\x06getBin\x12\x1d.pickingOperations.BinRequest\x1a\x1e.pickingOperations.BinResponse\"\x00\x12P\n\x0bpickProduct\x12\x1e.pickingOperations.PickRequest\x1a\x1f.pickingOperations.PickResponse\"\x00\x42M\n%grpc.smartWarehouse.pickingOperationsB\x1cPickingOperationServiceProtoP\x01\xa2\x02\x03POSb\x06proto3')



_BINREQUEST = DESCRIPTOR.message_types_by_name['BinRequest']
_BINRESPONSE = DESCRIPTOR.message_types_by_name['BinResponse']
_PICKREQUEST = DESCRIPTOR.message_types_by_name['PickRequest']
_PICKRESPONSE = DESCRIPTOR.message_types_by_name['PickResponse']
BinRequest = _reflection.GeneratedProtocolMessageType('BinRequest', (_message.Message,), {
  'DESCRIPTOR' : _BINREQUEST,
  '__module__' : 'pickingService_pb2'
  # @@protoc_insertion_point(class_scope:pickingOperations.BinRequest)
  })
_sym_db.RegisterMessage(BinRequest)

BinResponse = _reflection.GeneratedProtocolMessageType('BinResponse', (_message.Message,), {
  'DESCRIPTOR' : _BINRESPONSE,
  '__module__' : 'pickingService_pb2'
  # @@protoc_insertion_point(class_scope:pickingOperations.BinResponse)
  })
_sym_db.RegisterMessage(BinResponse)

PickRequest = _reflection.GeneratedProtocolMessageType('PickRequest', (_message.Message,), {
  'DESCRIPTOR' : _PICKREQUEST,
  '__module__' : 'pickingService_pb2'
  # @@protoc_insertion_point(class_scope:pickingOperations.PickRequest)
  })
_sym_db.RegisterMessage(PickRequest)

PickResponse = _reflection.GeneratedProtocolMessageType('PickResponse', (_message.Message,), {
  'DESCRIPTOR' : _PICKRESPONSE,
  '__module__' : 'pickingService_pb2'
  # @@protoc_insertion_point(class_scope:pickingOperations.PickResponse)
  })
_sym_db.RegisterMessage(PickResponse)

_PICKING = DESCRIPTOR.services_by_name['Picking']
if _descriptor._USE_C_DESCRIPTORS == False:

  DESCRIPTOR._options = None
  DESCRIPTOR._serialized_options = b'\n%grpc.smartWarehouse.pickingOperationsB\034PickingOperationServiceProtoP\001\242\002\003POS'
  _BINREQUEST._serialized_start=43
  _BINREQUEST._serialized_end=89
  _BINRESPONSE._serialized_start=91
  _BINRESPONSE._serialized_end=117
  _PICKREQUEST._serialized_start=119
  _PICKREQUEST._serialized_end=184
  _PICKRESPONSE._serialized_start=186
  _PICKRESPONSE._serialized_end=239
  _PICKING._serialized_start=242
  _PICKING._serialized_end=408
# @@protoc_insertion_point(module_scope)
